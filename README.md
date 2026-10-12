# Spring Boot JWT Authentication System

A RESTful backend application built with Spring Boot and Spring Security that implements JWT-based authentication, password encryption, and role-based authorization using MySQL.

## Features

- User registration and login
- JWT-based authentication
- Password hashing using BCrypt
- Stateless authentication using Spring Security
- Role-based authorization for `USER` and `ADMIN`
- Protected REST API endpoints
- Custom handling for missing, invalid, and expired JWTs
- MySQL database integration
- API testing using Postman

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot | Backend application framework |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Database operations |
| MySQL | Relational database |
| JJWT | JWT generation and validation |
| Maven | Dependency management |
| Postman | API testing |

## Project Structure

```text
src/main/java/org/security/springsecurityjwt/
├── config/
│   ├── JwtAuthenticationFilter.java
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── UserController.java
│   ├── AdminController.java
│   └── TestController.java
├── entity/
│   └── User.java
├── repository/
│   └── Userrepository.java
└── service/
    ├── AuthService.java
    ├── CustomUserDetailsService.java
    └── JwtService.java
```

## API Endpoints

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | Public |
| POST | `/auth/login` | Authenticate and obtain a JWT | Public |
| GET | `/test/hello` | Test JWT authentication | Authenticated |
| GET | `/user/profile` | Access the user profile endpoint | USER, ADMIN |
| GET | `/admin/dashboard` | Access the admin dashboard | ADMIN |

## Getting Started

### Prerequisites

- Java 17 or compatible JDK
- Maven
- MySQL Server
- IntelliJ IDEA or another Java IDE
- Postman

### 1. Clone the repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd <YOUR_PROJECT_FOLDER>
```

Replace the placeholders with your actual GitHub repository URL and project folder name.

### 2. Create the database

Execute the following SQL in MySQL:

```sql
CREATE DATABASE springsecurityjwt;
```

### 3. Configure environment variables

Configure the following environment variables in your IDE's run configuration:

```text
DB_URL=jdbc:mysql://localhost:3306/springsecurityjwt
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_base64_encoded_secret_key
```

Reference these variables in `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=${JWT_SECRET}
server.port=8091
```

Use a securely generated Base64-encoded JWT signing key of sufficient length. Never commit real credentials or signing keys to GitHub.

### 4. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, run the main Spring Boot application class from your IDE.

The application runs at:

```text
http://localhost:8091
```

## Testing with Postman

### Register a user

**POST** `/auth/register`

Select Body → raw → JSON and provide the fields expected by your registration DTO or entity.

Example, if your endpoint accepts these fields:

```json
{
  "username": "testuser",
  "password": "your-password"
}
```

### Login

**POST** `/auth/login`

Provide your registered username and password. Copy the JWT returned by the API.

### Access a protected endpoint

**GET** `/test/hello`

In Postman, select Authorization → Bearer Token and enter the JWT.

Use the same method to test `/user/profile` and `/admin/dashboard` with accounts that have the appropriate roles.

## HTTP Status Codes

| Status | Meaning |
|---|---|
| `200 OK` | Request successful |
| `201 Created` | Resource created, if returned by the endpoint |
| `400 Bad Request` | Invalid request |
| `401 Unauthorized` | Missing, invalid, or expired authentication token |
| `403 Forbidden` | Authenticated user lacks the required role |

## Security Highlights

- Passwords are hashed using BCrypt before storage.
- JWTs are validated before protected endpoints are accessed.
- Spring Security enforces role-based authorization.
- Stateless session management is configured.
- Database credentials and JWT signing keys should be supplied through environment variables.

## Future Enhancements

- Request validation and consistent error responses
- Swagger/OpenAPI documentation
- Unit and integration tests using JUnit and Mockito
- Refresh-token rotation and revocation
- Docker support
- Deployment to a cloud platform

## Author

**Lakshmikar Katam**

Java Backend Developer | Spring Boot | Spring Security | REST APIs | MySQL
