
# Spring Boot Assignment

Creating users and preforming CRUD operations on users data by updating the user,deleting the user.
## Implemented Files

  Implented files:
- `src/main/java/controller/UserController.java`
- `src/main/java/model/UserController.java`
- `src/main/java/controller/Manager.java`
- `src/main/java/controller/User.java`
- `src/main/java/repository/Manager.java`
- `src/main/java/repository/User.java`
- `src/main/java/Service/UserService.java`
- `src/main/java/Service/UserController.java`

## Technologies used
Spring boot

Maven

MySql(v8.0)

JAVA(jdk v22.0)

## Database Schema

-- Created the users table


    CREATE TABLE users (
        user_id BINARY(16) PRIMARY KEY,
        full_name VARCHAR(32) NOT NULL,
        mob_num VARCHAR(20) NOT NULL,
        pan_num VARCHAR(20) NOT NULL,
        manager_id BINARY(16),
        created_at DATETIME,
        updated_at DATETIME,
        is_active BOOLEAN
    );



-- Created the managers table


    CREATE TABLE managers (
        manager_id BINARY(16) PRIMARY KEY,
        full_name VARCHAR(32) NOT NULL,
        mob_num VARCHAR(20) NOT NULL
        );
## Application instructions 

start the application by using the command       :      mvnspring-boot:run
## Dependencies Used

1)Spring Boot Starter Web

2)Spring Boot Starter Data JPA

3)H2 Database

4)MySQL Connector/J:
