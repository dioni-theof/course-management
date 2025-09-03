# Course Management System

A modern course management system built with Spring Boot and Java, 
designed to efficiently handle educational course operations and student management. 
The system provides RESTful APIs for managing courses, students, and course registrations.

## 🚀 Features

- Course Management
    - Create, read, update, and delete courses
    - Search courses by number
    - Paginated course listing
- Student Management
    - Student registration and profile management
    - Course enrollment for students
    - Student-course relationship tracking
- Course Registration
    - Track registration dates
    - Manage semester information
    - Handle student-course associations
- RESTful API endpoints
- Pagination support for large datasets
- Docker support for easy deployment
- PostgreSQL database integration

## 🛠️ Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- SpringDoc OpenAPI
- PostgreSQL
- Docker
- Maven
- JSON for API communication

## 📋 Prerequisites

- Java 21 or higher
- Docker and Docker Compose
- Maven
- PostgreSQL (provided via Docker)

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
├── src/
│   ├── main/
│   │   ├── java/org/sonja/course_management/
│   │   │   ├── controller/         # REST API endpoints
│   │   │   ├── entities/           # JPA entities
│   │   │   ├── exeptions/          # Custom exceptions
│   │   │   ├── mapping/            # Object mapping utilities
│   │   │   ├── models/            # Data transfer objects
│   │   │   ├── repo/              # Data repositories
│   │   │   ├── service/           # Business logic
│   │   │   └── BookLessonApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/org/sonja/course_management/
│       └── BookLessonApplicationTests.java
├── .mvn/ # maven wrapper
├── https/ # examples http requests
├── docker-compose.yml
└── pom.xml
```

## 🚀 API Endpoints

### Courses
- `POST /api/courses` - Create a new course
    - Request body: CourseRequestAndResponse (numberCourse, nameCourse)
    - Creates a new course in the system
- `GET /api/courses/all` - Get all courses
    - Query parameters:
        - size (default: 5) - Items per page
        - page (default: 0) - Page number
    - Returns paginated list of courses
- `GET /api/courses/{numberCourse}/findbynumber` - Find course by number
    - Path parameter: numberCourse
    - Returns detailed course information
- `PUT /api/courses/{numberCourse}/update` - Update course
    - Path parameter: numberCourse
    - Request body: UpdateCourseRequest (nameCourse)
    - Updates course information
- `DELETE /api/courses/{numberCourse}/delete` - Delete course
    - Path parameter: numberCourse
    - Removes the course from the system
    - Returns success message

### Students
- `POST /api/students` - Create a new student
    - Request body: StudentRequest (name, surname, birthday, email, infoCourses)
    - Creates a student and optionally enrolls them in courses
- `GET /api/students/{email}/all-course` - Get student details with all enrolled courses
    - Returns student information and their course registrations
- `PUT /api/students/{email}/update-student` - Update student information
    - Request body: StudentRequest (can update name, surname, birthday, and course enrollments)
- `DELETE /api/students/{email}/delete-student` - Delete a student
    - Removes the student and their course registrations


## 📝 Documentation

API documentation is available at `http://localhost:8080/swagger-ui.html` when running the application.
