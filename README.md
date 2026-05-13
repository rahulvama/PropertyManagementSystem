# Property Management System

A Spring Boot REST API with a simple HTML frontend for managing properties and property managers.

## Tech Stack
- Java 17, Spring Boot 3.x
- Spring Data JPA, H2 Database
- JUnit 5, Mockito
- Maven, Docker

## Features
- Add, update, delete properties
- Filter by area and occupancy
- Calculate manager salary (10% of rental price)
- Simple HTML frontend included
- 8 JUnit unit tests

## How to Run Locally
```bash
git clone https://github.com/rahulvama/PropertyManagementSystem.git
cd PropertyManagementSystem/PropertyManagementSystem
mvn spring-boot:run
```
Open: http://localhost:8080

## Run with Docker
```bash
mvn package
docker build -t property-management .
docker run -p 8080:8080 property-management
```

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/properties | Add property |
| GET | /api/properties | Get all |
| GET | /api/properties/{id} | Get by ID |
| PUT | /api/properties/{id} | Update |
| DELETE | /api/properties/{id} | Delete |
| GET | /api/properties/area/{area} | Filter by area |
| GET | /api/properties/occupied | Occupied only |
| GET | /api/properties/manager/{name} | By manager |
| GET | /api/properties/manager/{name}/salary | Manager salary |
