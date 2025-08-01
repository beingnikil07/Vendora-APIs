# 🛍️ Vendora - Scalable E-commerce Backend with Spring Boot

**Vendora** is a full-fledged backend system developed in Java using Spring Boot, designed for building modern, secure, and scalable e-commerce platforms. The application manages essential features such as user registration, product management, shopping cart, and order processing.

Vendora follows layered architecture principles — separating concerns between controllers, services, and repositories. It uses RESTful APIs to ensure smooth communication between frontend and backend, making it ideal for single-page applications (SPAs) developed in React, Angular, or mobile apps.

The system supports both admin and user roles. Admins can manage product listings, while users can register, browse products, add items to their cart, and place orders.

This project is perfect for learning backend development, understanding real-world use cases of Spring Boot, or for startups looking to build a scalable online store.

---

## 🚀 Features

- User registration & login (with role-based access)
- Product CRUD (Create, Read, Update, Delete)
- Shopping cart functionality
- Order creation and management
- Structured error handling
- DTO usage for API communication
- Clean code architecture (Controller → Service → Repository)
- MySQL database support

---

## ⚙️ Tech Stack

- **Language**: Java 21
- **Framework**: Spring Boot,Spring Security
- **Database**: MySQL
- **ORM**: Hibernate / Spring Data JPA
- **Documentation**: Swagger/OpenAPI Documentation 
- **API Format**: REST (JSON)
- **Build Tool**: Maven

---

## 📂 Project Structure

```
Vendora/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/vendora/
│   │   │       ├── controllers/        # REST Controllers
│   │   │       ├── services/           # Business Logic
│   │   │       ├── repository/         # JPA Repositories
│   │   │       ├── models/             # Entity Classes
│   │   │      
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/ or templates/   # Optional
│
├── pom.xml
├── README.md
├── .gitignore
```

---

## 🧪 Sample API Endpoints

| Method | Endpoint | Description                  |
|--------|--------|------------------------------|
| POST   | `/api/auth/register` | Register new user            |
| POST   | `/api/auth/login` | Login user                   |
| GET    | `/api/products/getProducts` | List all products            |
| POST   | `/api/products/create` | Add a new product (Admin)    |
| POST   | `/api/cart/add` | Add product to cart          |
| POST   | `/api/order/create` | Create order                 |


---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/beingnikil07/Vendora-APIs.git
cd vendora
```

### 2. Configure MySQL

Create a database named `vendora_db` and update the credentials in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vendora_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
### 3. Run the Application

```bash
./mvnw spring-boot:run
```

Or run `VendoraApplication.java` from your IDE.

---

## 📄 .gitignore

```gitignore
/target
*.log
*.class
*.env
*.iml
.idea/
.DS_Store
```

---

## 👤 Author

**Nikil Kumar**  
Java Backend Developer | Spring Boot Enthusiast  
GitHub: [beingnikil07](https://github.com/yourusername)  
LinkedIn: [linkedin.com/in/nikilkumar07](https://linkedin.com/in/nikilkumar07)

---

> ⭐ Star this repo if you found it helpful!