# Course Management System

A modern course management system built with Spring Boot and Java, designed to efficiently handle educational course operations and management.

## 🚀 Features

- Course creation and management
- RESTful API endpoints
- Docker support for easy deployment
- Database integration

## 🛠️ Technologies

- Java
- Spring Boot
- Docker
- Maven
- PostgreSQL (via Docker)

## 📋 Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Maven

## 🔧 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/dioni-theof/course-management.git
   cd course-management
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Start the application with Docker:
   ```bash
   docker-compose up
   ```

The application will be available at `http://localhost:8080`

## 🏗️ Project Structure

```
course-management/
├── src/                    # Source files
├── .mvn/                   # Maven wrapper files
├── docker-compose.yml      # Docker compose configuration
├── pom.xml                 # Maven dependencies
└── https/                  # HTTPS configuration
```

## 🚀 Usage

After starting the application, you can access the API endpoints to manage courses. Detailed API documentation will be available at `http://localhost:8080/swagger-ui.html`

## 🤝 Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is currently not under any specific license. All rights reserved.

## 📞 Contact

Dionisis Theofanous - [@dioni-theof](https://github.com/dioni-theof)

Project Link: [https://github.com/dioni-theof/course-management](https://github.com/dioni-theof/course-management)