# Exam Seating Arrangement System

A backend system built with **Spring Boot** that automatically generates exam seating arrangements for educational institutions.
The system manages **students, exam sessions, rooms, and seating plans**, ensuring organized allocation of seats and enabling **quick seat lookup by roll number**.

This project demonstrates a **scalable backend architecture with authentication, database integration, and automated seat allocation logic**.

---

# Project Overview

Managing exam seating manually becomes difficult when dealing with hundreds or thousands of students.
This system automates the process by:

* Managing student records
* Managing exam rooms and capacity
* Creating exam sessions
* Assigning students to sessions
* Generating seating plans automatically
* Allowing students to check their seat using their roll number

---

# Features

### Authentication

* User registration
* Secure login
* JWT-based authentication

### Student Management

* Add and manage student records
* Store roll number, department, and year

### Room Management

* Add exam rooms
* Automatically calculate room capacity using rows × columns

### Exam Session Management

* Create exam sessions with course code, date, and time
* Assign students to sessions

### Seating Plan Generation

* Automatically generate seating plans based on available rooms and students
* Assign seats systematically

### Seat Lookup

Students can find their seat using their roll number.

Example response:

```
Roll Number: CSE001
Room: A101
Seat: R1C1
Course: CS301
Time: 10:00 AM
```

---

# Technology Stack

Backend Framework

* Spring Boot

Security

* Spring Security
* JWT Authentication

Database

* MySQL

ORM

* Hibernate / JPA

Build Tool

* Maven

API Testing

* Postman

Version Control

* Git & GitHub

---

# Project Structure

```
src/main/java/com/example/demo

config        → Security configuration
controller    → REST API controllers
dto           → Request and response objects
exception     → Custom exception handling
model         → Entity classes
repository    → JPA repositories
security      → JWT authentication components
service       → Business logic layer
```

Architecture used:

```
Controller → Service → Repository → Database
```

---

# API Endpoints

## Authentication

Register User

POST

```
/api/auth/register
```

Example Request

```json
{
  "name": "Admin User",
  "email": "admin@test.com",
  "password": "123456",
  "role": "ADMIN"
}
```

Login

POST

```
/api/auth/login
```

---

# Students

Create Student

POST

```
/api/students
```

Get All Students

GET

```
/api/students
```

---

# Rooms

Create Room

POST

```
/api/rooms
```

Example Request

```json
{
  "roomNumber": "A101",
  "rows": 5,
  "columns": 6
}
```

Capacity is automatically calculated.

Get All Rooms

GET

```
/api/rooms
```

---

# Exam Sessions

Create Session

POST

```
/api/sessions
```

Example Request

```json
{
  "courseCode": "CS301",
  "examDate": "2026-04-01",
  "examTime": "10:00 AM"
}
```

Get Session

GET

```
/api/sessions/{id}
```

Assign Student to Session

POST

```
/api/sessions/{sessionId}/students/{studentId}
```

Get Students in Session

GET

```
/api/sessions/{sessionId}/students
```

---

# Seating Plan

Generate Seating Plan

POST

```
/api/seating-plans/generate/{sessionId}
```

Get Seating Plan

GET

```
/api/seating-plans/{planId}
```

Get Plans by Session

GET

```
/api/seating-plans/session/{sessionId}
```

---

# Seat Lookup (Student Feature)

Students can check their seat using their roll number.

GET

```
/api/seating-plans/seat/{rollNumber}
```

Example

```
/api/seating-plans/seat/CSE001
```

Example Response

```json
{
  "rollNumber": "CSE001",
  "room": "A101",
  "seat": "R1C1",
  "courseCode": "CS301",
  "examTime": "10:00 AM"
}
```

---

# Database Schema

Main tables used:

* users
* students
* exam_rooms
* exam_sessions
* seating_plans
* session_students

---

# Error Handling

The system includes validation and exception handling for cases such as:

* Duplicate email registration
* Duplicate room numbers
* Invalid exam dates
* Missing students
* Session not found
* Room capacity issues

---

# How to Run the Project

Clone the repository

```
git clone https://github.com/yourusername/exam-seating-arrangement-system.git
```

Navigate to project

```
cd exam-seating-arrangement-system
```

Run the application

```
mvn spring-boot:run
```

Server runs at

```
http://localhost:8080
```

---

# Example Workflow

1. Register admin user
2. Login to obtain JWT token
3. Add students
4. Add exam rooms
5. Create exam session
6. Assign students to session
7. Generate seating plan
8. Students check seat using roll number

---

# Future Enhancements

Possible improvements for real-world deployment:

* Department-based seating separation
* Bench capacity rules
* Prevent same department adjacency
* Room optimization algorithm
* Seating plan PDF export
* Excel export for invigilators
* Admin dashboard
* Invigilator assignment
* Multiple session support
* Advanced seating algorithms

---

# Author

Dhanush

GitHub
https://github.com/User-Dhanush-06

---

# License

This project is open source and available under the MIT License.
