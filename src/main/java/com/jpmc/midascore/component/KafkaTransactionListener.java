package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.jpmc.midascore.repository.UserRepository;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;


@Component
public class KafkaTransactionListener {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;



    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core-group")
    public void listen(Transaction transaction) {

        System.out.println("Received: " + transaction);

        UserRecord sender = userRepository
                .findById(transaction.getSenderId())
                .orElse(null);

        UserRecord recipient = userRepository
                .findById(transaction.getRecipientId())
                .orElse(null);

        if (sender == null || recipient == null) {
            return;
        }

        if (sender.getBalance() < transaction.getAmount()) {
            return;
        }

        System.out.println("Received: " + transaction);

        String url = "http://localhost:8080/incentive";

        Incentive incentive = restTemplate.postForObject(
                url,
                transaction,
                Incentive.class
        );

        float incentiveAmount = (float) incentive.getAmount();
        float transactionAmount = transaction.getAmount();

        sender.setBalance(sender.getBalance() - transactionAmount);

        recipient.setBalance(
                recipient.getBalance() + transactionAmount + incentiveAmount
        );

        userRepository.save(sender);
        userRepository.save(recipient);

        System.out.println("Incentive received: " + incentive.getAmount());
    }

}
