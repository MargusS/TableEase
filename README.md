# 🍽️ Tablease - Distributed Restaurant Order Management System

This project is a distributed microservices-based solution that simulates a real-world restaurant order system. It's designed to showcase modern software architecture skills, including Domain-Driven Design (DDD), Clean Architecture, asynchronous event communication, and containerized deployment.

---

## 🎯 Project Goals

- ✅ Build a realistic system for managing restaurant orders.
- ✅ Apply Clean Architecture and DDD principles.
- ✅ Use events to communicate between microservices (Apache Kafka).
- ✅ Deploy all services with Docker and orchestrate using `docker-compose`.
- ✅ Integrate CI/CD with GitHub Actions.

---

## 🧱 Overall Architecture

![Architecture](docs/architecture-diagram.png) <!-- Upload your own diagram later -->

- **Gateway**: Entry point for client, waiter, kitchen, and bar apps.
- **Comanda Service**: Creates orders and splits items based on destination (bar/kitchen).
- **Kitchen & Bar Services**: Handle relevant orders and update their statuses.
- **Notification Service**: Sends notifications to end-users or staff.
- **Events**: Services communicate through events via Kafka.

---

## 🗂️ Repository Structure (Monorepo)

```
comandero-project/
├── comanda-service/         # Main service to create orders
├── cocina-service/          # Handles kitchen-related orders
├── barra-service/           # Handles bar-related orders
├── notificacion-service/    # Notification service (push/email)
├── gateway/                 # API Gateway
├── shared/                  # Common events and utilities
├── infra/                   # Kafka, databases, nginx
├── docker-compose.yml       # Service orchestration
└── .github/workflows/ci.yml # CI/CD pipeline with GitHub Actions
```

---

## 🚀 How to Run the Project

> Requirements: Docker and Docker Compose

```bash
# Launch all services
docker-compose up --build
```

This will start:

- Kafka + Zookeeper
- Postgres (one per microservice)
- All Java microservices
- Gateway

---

## ⚙️ Microservices Overview

| Service              | Port | Description                                  |
|----------------------|------|----------------------------------------------|
| comanda-service      | 8081 | Creates orders and splits them               |
| cocina-service       | 8082 | Manages kitchen-specific orders              |
| barra-service        | 8083 | Manages bar-specific orders                  |
| notificacion-service | 8084 | Sends notifications to users/waiters         |
| gateway              | 8080 | API Gateway routing requests to services     |

---

## 🧪 CI/CD

- Automated build and test validation via GitHub Actions.
- Configuration available at `.github/workflows/ci.yml`.

---

## 🧠 Technologies Used

- Java 17 + Spring Boot
- Kafka
- PostgreSQL
- Docker & Docker Compose
- Clean Architecture + DDD
- GitHub Actions

---

## 📌 Project Status

✅ Architecture defined  
✅ `comanda-service` under development  
🚧 Kitchen, bar, and notification services in progress  
🚀 CI/CD implementation ongoing

---

## 📬 Contact

- LinkedIn: [your-linkedin]
- GitHub: [your-github]
- Email: [your-email]

---

_Feedback and suggestions are welcome!_
