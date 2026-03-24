# JPMorganChase Software Engineering Job Simulation – Forage

This repository contains my implementation of the JPMorganChase Software Engineering Virtual Experience Program hosted on Forage (February 2026).

The project simulates a backend microservice responsible for processing high-volume financial transactions, validating business logic, persisting data, and integrating with external APIs.

---

## 📌 Project Overview

The system is built as a Spring Boot microservice that:

- Consumes transaction events from Apache Kafka
- Validates and persists transaction data using Spring Data JPA
- Updates relational User balances
- Integrates with an external Incentive REST API
- Exposes REST endpoints for querying account balances
- Includes unit and integration testing using Maven and embedded Kafka

The architecture emphasizes clean layering, transactional consistency, and reliability across message ingestion and API interactions.

---

## 🏗️ Architecture & Flow

1. Kafka Producer sends transaction message
2. Spring Boot Consumer deserializes and validates message
3. Business logic processes transaction
4. Data persisted using JPA (H2 database)
5. External Incentive API called via RestTemplate
6. Updated balance returned through REST endpoint

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Apache Kafka
- Spring Data JPA
- H2 Database
- REST (RestTemplate)
- Maven
- JUnit
- Embedded Kafka (for testing)

---

## 🔍 Key Engineering Concepts Demonstrated

- Event-driven microservices architecture
- Message deserialization and stream processing
- Transactional data consistency
- Relational entity modeling
- REST API integration
- Unit & integration testing
- Clean layered architecture

---

## 📄 Certificate

Certificate of Completion:  
![View Certificate](./certificate/Forage.pdf)

---

## 🚀 What I Learned

- Designing production-style microservices
- Handling asynchronous event streams
- Integrating third-party APIs within transactional workflows
- Writing reliable test suites for message-driven systems
- Maintaining separation of concerns in Spring architecture

---
  
