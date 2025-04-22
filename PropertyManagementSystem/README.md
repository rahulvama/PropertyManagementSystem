
# 🏠 Property Management System

A Spring Boot application built for the **Arborgold Real Estate Programming Challenge – Interview Round 2**. This project manages properties and property managers, providing CRUD operations and business logic via a RESTful API.

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Jakarta Bean Validation
- Maven

## 📦 Project Structure

```
com.propertymanager
├── controller        # REST API endpoints
├── service           # Business logic layer
├── repository        # Spring Data JPA interfaces
├── dto               # Request DTOs with validation
├── entity            # JPA entity classes
├── exception         # Custom and global error handling
└── PropertyManagementApplication.java  # Main class
```

## 📌 Features

- Add a property (auto-creates manager if they don't exist)
- Update a property value with manager credentials
- Get property by ID
- Filter properties by area or occupancy
- Find property with the lowest value
- Count properties per manager
- Calculate salary for a manager (10% of occupied property value)

## 🧪 Testing

🚫 Tests not included. The developer is actively learning **JUnit** and **Mockito** to strengthen test-writing skills.

## 🛠 How to Run

1. Clone the project or unzip the folder.
2. Navigate into the project root.
3. Run the app:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access API via Postman at `http://localhost:8080/api/properties`

## 📂 Sample API Endpoints

| Method | Endpoint                                | Description                        |
|--------|------------------------------------------|------------------------------------|
| POST   | `/api/properties`                        | Add a new property                 |
| PUT    | `/api/properties/{id}/update`            | Update property value              |
| GET    | `/api/properties/{id}`                   | Get property by ID                 |
| GET    | `/api/properties`                        | Get all properties                 |
| GET    | `/api/properties/area/{area}`            | Get properties by area             |
| GET    | `/api/properties/occupied`               | Get all occupied properties        |
| GET    | `/api/properties/lowest-value`           | Get property with lowest value     |
| GET    | `/api/properties/manager/{name}/salary`  | Get manager's salary               |
| GET    | `/api/properties/manager/{name}/count`   | Get count of properties by manager |

## 📃 Note

- Uses H2 in-memory DB, so data resets on restart.
- All manager-related operations use `name` as the primary key.

---

💬 Feel free to reach out for any clarifications or walkthrough.

