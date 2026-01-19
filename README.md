# Pixel Peel 🎨

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-green?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=for-the-badge)
![JWT](https://img.shields.io/badge/Jwt-Auth-7371F7?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/Postgres-8.0-7AADFF?style=for-the-badge)
![Licence](https://img.shields.io/badge/Licence-MIT-pink?style=for-the-badge)

## About ✨

Pixel Peel is a RESTful API built to support a digital stickers e-commerce platform, handling products, users, admins, cart and orders.

<br/>

## Features ⚒️
- JWT authentication
- User roles
- Product and order management
- RESTful endpoints for CRUD operations

<br/>

## Installation 💻

1. **Clone the repository:**

   ```sh
   git clone https://github.com/liviadfsilva/PixelPeelAPI.git
   cd PixelPeelAPI
   ```

2. **Copy the .env.example file to .env and change the environment variables if necessary:**

   ```sh
   cp .env.example .env
   ```

3. **Start the database using Docker**

   ```bash
    docker-compose up -d
   ```

4. **Run the application**

   ```bash
    mvn spring-boot:run
    ```
<br/>

#### The application will start at:
꩜ **http://localhost:8080**

#### Access the Swagger documentation:
📜 **http://localhost:8080/swagger-ui/index.html**

<br/>

## Project Structure 🦴
```
pixelpeel/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/liviasilva/pixelpeel/
│   │   │       ├── Auth
│   │   │       │   └── controller
│   │   │       │   └── dto
│   │   │       │   └── jwt
│   │   │       │   └── model
│   │   │       │   └── service
│   │   │       ├── Cart
│   │   │       │   └── controller
│   │   │       │   └── dto
│   │   │       │   └── model
│   │   │       │   └── repository
│   │   │       │   └── service
│   │   │       ├── config
│   │   │       │   └── AuthorisationService.java
│   │   │       │   └── OpenAPIConfig.java
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── Order
│   │   │       │   └── controller
│   │   │       │   └── dto
│   │   │       │   └── model
│   │   │       │   └── repository
│   │   │       │   └── service
│   │   │       ├── Sticker
│   │   │       │   └── controller
│   │   │       │   └── dto
│   │   │       │   └── model
│   │   │       │   └── repository
│   │   │       │   └── service
│   │   │       ├── User
│   │   │       │   └── controller
│   │   │       │   └── dto
│   │   │       │   └── model
│   │   │       │   └── repository
│   │   │       │   └── service
│   │   │       └── PixelpeelApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │
│   └── test/
│
└── .env.example
└── .gitignore
└── docker-compose.yml
└── LICENCE
└── pom.xml
```

<br/>

## Inspiration 🌱

This project was born from my love of stickers as a way to explore how a backend API for selling digital products could be designed and implemented.

<br/>

## Future Improvements 🕰️

- Integration with a payment provider
- Secure download links for purchased stickers

<br/>

## Author 🌸
**Lívia Silva**<br/>
Backend Developer

<br/>

- GitHub: https://github.com/liviadfsilva 
- LinkedIn: https://linkedin.com/in/liviadfsilva


## Licence 📋
This software is licenced under the MIT Licence.
