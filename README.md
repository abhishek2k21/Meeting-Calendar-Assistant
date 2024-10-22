# Meeting Calendar Assistant

This project is a backend REST API developed using Spring Boot. The Meeting Calendar Assistant helps employees book meetings, find available slots, and check for scheduling conflicts. It incorporates user registration and error handling to ensure smooth operation.

## ER Diagram
```
Meeting-Calendar-Assistant/ 

Meeting-Calendar-Assistant/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── meetingcalendar/
│                   ├── controller/
│                   │   └── MeetingController.java
│                   ├── model/
│                   │   └── Meeting.java
│                   ├── service/
│                   │   └── MeetingServiceImpl.java
│                   ├── repository/
│                   │   └── MeetingRepository.java
│                   └── MeetingCalendarAssistantApplication.java
├── resources/
│   ├── application.properties
│   ├── static/
│   ├── templates/
│   └── db/
│       └── migration/
├── test/
│   └── java/
│       └── com/
│           └── meetingcalendar/
│               └── MeetingServiceTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Tech Stack

- **Java**
- **Spring Framework**
- **Spring Boot (v3.2.5)**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**

## Modules

- **Login Module**
- **Meeting Booking Module**
- **Employee Module**

## Features

- **Employee Registration**: Employees can register themselves in the calendar system.
- **Meeting Booking**: Employees can book meetings at specified times. The system validates if the employee is available at the requested time.
- **Viewing Employees**: Retrieve a list of all employees and their scheduled meetings.
- **Finding Free Slots**: Check available time slots between two employees for meetings.
- **Conflict Resolution**: Get a list of conflicts for a specified time.

## Installation & Run

Before running the API server, update the database configuration in the `application.properties` file. Adjust the port number, username, and password according to your local database configuration.

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=your_username
spring.datasource.password=your_password
```
## API Root Endpoint
- Swagger UI
# API Module Endpoints
- Login Module
- POST /register/employee: Register a new employee.
# Meeting Module
- POST /book/meeting: Book a meeting for an employee.
- GET /employees: Get a list of all employees with their meetings.
- GET /freeSlots/{emp1}/{emp2}: Get available time slots between two employees.
- GET /conflicts/time: Get meeting conflicts for a specific time.


## Sample API Response for Meeting Booking
Request Body (JSON)

json
```Copy code
{
  "employee": 1,
  "date": "2022-06-10",
  "time": "10:00 AM",
  "duration": "1 hour"
}
Response (JSON)
```

json
```Copy code
{
  "message": "Meeting with Employee ID=1 booked on 2022-06-10 at 10:00 AM"
}
```
# Testing
You can test the API using Postman by setting up the appropriate requests according to the API endpoints described above. Ensure that you have the server running and that the database is correctly configured.


