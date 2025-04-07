
# Spring Boot eCommerce Microservices Project

A hands-on project to explore microservices architecture using Spring Boot, Kafka, MongoDB, PostgreSQL, and more. This project simulates a basic eCommerce platform broken into multiple independently deployable services.

---

## 🚀 Tech Stack

- **Spring Boot (Java)**
- **Docker & Docker Compose**
- **PostgreSQL & pgAdmin**
- **MongoDB & Mongo Express**
- **Kafka & Zookeeper**
- **Zipkin (Distributed Tracing)**
- **MailDev (Email Testing)**

---

## 🧩 Services Overview

| Service        | Port     | Description                          |
|----------------|----------|--------------------------------------|
| PostgreSQL     | `5432`   | Relational database for core data    |
| pgAdmin        | `5050`   | GUI to manage PostgreSQL             |
| MongoDB        | `27017`  | NoSQL DB for catalog, products, etc. |
| Mongo Express  | `8081`   | GUI to manage MongoDB                |
| Kafka          | `9092`   | Messaging for event-driven services  |
| Zookeeper      | `22181`  | Required for Kafka                   |
| Zipkin         | `9411`   | Distributed tracing visualization    |
| MailDev        | `1080` / `1025` | Fake SMTP server for dev emails |

---

## 🐳 Docker Compose Setup

To spin up the whole infrastructure:

```bash
docker-compose up -d
```

This will create a bridge network and run all services in isolation.

---

## 🔐 Default Credentials

| Service       | Username                | Password |
|---------------|-------------------------|----------|
| PostgreSQL    | `thmz`                  | `thmz`   |
| pgAdmin       | `pgadmin@pgadmin.org`   | `admin`  |
| MongoDB       | `thmz`                  | `thmz`   |
| Mongo Express | `thmz`                  | `thmz`   |

---

## 🧠 Project Structure (Suggested)

```
├── services/
│   ├── gateway/        # API Gateway
│   ├── config-server/  # Centralized config management
│   ├── customer/       # Customer management
│   ├── discovery/      # Service discovery (e.g., Eureka)
│   ├── notification/   # Email, SMS, and push notifications
│   ├── payment/        # Payment processing service
│   ├── order/          # Order management
│   └── product/        # Product catalog management
└── docker-compose.yml 
```

> Each service is a Spring Boot application that communicates over REST or Kafka, registered via Eureka, and managed using Spring Cloud Config.

---

## 🧪 Development

- Use **Zipkin** to trace inter-service calls: http://localhost:9411  
- Use **pgAdmin** and **Mongo Express** to inspect your relational and NoSQL data easily.  
- **Kafka** topics can be used for asynchronous communication
- Use **MailDev** at http://localhost:1080 to see outgoing emails.

---

## 🔍 Observability

| Tool         | Usage                    |
|--------------|--------------------------|
| Zipkin       | Distributed Tracing      |
| MailDev      | Email Debugging          |
| pgAdmin      | PostgreSQL GUI           |
| Mongo Express| MongoDB GUI              |