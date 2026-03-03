# 🚀 CEP Address Manager - Fullstack Application

A fullstack web application for managing users with automatic address retrieval based on Brazilian ZIP codes (CEP).

This project was developed as a technical challenge for a Full-Stack position, demonstrating clean architecture, validation, API integration, and modern frontend practices.

---

## 📌 Project Overview

The application allows users to:

- Create users with name, CPF, and CEP
- Automatically fetch address information from ViaCEP API
- List all saved users
- Update existing users
- Delete users
- Validate CPF, CEP, and required fields
- Handle errors professionally with proper HTTP status codes
- Display responsive and modern UI

---

## 🛠 Tech Stack

### 🔹 Backend
- Java 17
- Spring Boot 4
- Spring Data JPA
- PostgreSQL
- Bean Validation (Jakarta Validation)
- RESTful API
- Global Exception Handling
- CORS Configuration

### 🔹 Frontend
- React 18
- Axios
- Manual input masking (CPF & CEP)
- Responsive CSS
- Modern UI design

---

## 🧠 Architecture Highlights

### Backend

- Clean separation of layers:
  - Controller
  - Service
  - Repository
  - DTO
  - Entity
  - Exception Handler
- DTO used to decouple API layer from persistence layer
- Global error handling with `@RestControllerAdvice`
- CPF uniqueness validation (HTTP 409 Conflict)
- Bean Validation:
  - `@NotBlank`
  - `@Pattern`
  - `@Size`
- Automatic address lookup via ViaCEP API
- Centralized CORS configuration

### Frontend

- Axios integration with backend API
- CPF and CEP masking compatible with React 18
- Form validation feedback
- Success and error visual alerts
- Delete confirmation dialog
- Responsive layout
- Clean and organized UI

---

## 📂 Project Structure

cep-address-manager-fullstack/
│
├── backend/
│ ├── controller/
│ ├── service/
│ ├── repository/
│ ├── entity/
│ ├── dto/
│ ├── exception/
│ ├── config/
│ └── application.properties
│
├── frontend/
│ ├── src/
│ │ ├── App.js
│ │ ├── App.css
│ │ └── ...
│ └── package.json
│
└── README.md


---

## ⚙️ How to Run the Project

### 🔹 1. Clone the Repository

```bash
git clone https://github.com/fp-torres/cep-address-manager-fullstack.git
cd cep-address-manager-fullstack

🔹 Backend Setup
Configure PostgreSQL

Create a database:

CREATE DATABASE cep_address_manager_fullstack;

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/cep_address_manager_fullstack
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Run Backend

cd backend
./mvnw spring-boot:run

Backend will run at:

http://localhost:8080

🔹 Frontend Setup

cd frontend
npm install
npm start

Frontend will run at:

http://localhost:3000

🔌 API Endpoints
Create User
POST /usuarios
List Users
GET /usuarios
Update User
PUT /usuarios/{id}
Delete User
DELETE /usuarios/{id}

📥 Example Request (Create User)
{
  "nome": "Felipe Torres",
  "cpf": "12345678901",
  "cep": "20040002"
}

The backend automatically fetches:

logradouro

bairro

cidade

estado

❗ Error Handling
Scenario	HTTP Status
Invalid input	400 Bad Request
Duplicate CPF	409 Conflict
User not found	400 / 404
Server error	500
🎨 UI Features

Modern gradient layout

Card-based design

Responsive layout

CPF and CEP formatted automatically

Visual success and error feedback

Delete confirmation dialog

🔐 Validation Rules

Name: required, 3–100 characters

CPF: exactly 11 digits

CEP: exactly 8 digits

CPF must be unique

🚀 Future Improvements

Edit user functionality in frontend

Auto-fetch CEP while typing

Toast notifications

Loading animations

Authentication layer

Deployment (Render + Vercel)

👨‍💻 Author

Felipe Torres
Full-Stack Developer

📄 License

This project was developed for technical evaluation purposes.