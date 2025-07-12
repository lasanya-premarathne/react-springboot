# Event Ticketing System

A Spring Boot application that implements a concurrent ticket management system for events, handling ticket distribution between vendors and customers.

## Overview

This system simulates a real-world event ticketing platform where:
- Vendors can add tickets to a central ticket pool
- Customers can purchase tickets from the pool
- The system handles concurrent operations safely
- Configurable system constraints manage ticket flow

## Technologies Used

- Java 19
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## System Architecture

### Core Components

1. *Models*
    - CustomerModel: Represents event ticket customers
    - VendorModel: Represents ticket vendors
    - TicketModel: Represents individual tickets
    - SysConModel: Contains system configuration parameters

2. *Services*
    - CustomerService: Manages customer operations
    - VendorService: Handles vendor operations
    - TicketPoolService: Core service managing ticket operations
    - SysConService: Manages system configuration
    - RandomFourDigitGenerationService: Generates unique IDs

3. *Controllers*
    - CustomerController: Customer-related endpoints
    - VendorController: Vendor-related endpoints
    - TicketController: Ticket operation endpoints
    - SysConController: System configuration endpoints

## Features

- *Concurrent Ticket Management*
    - Thread-safe ticket pool operations
    - Synchronized ticket addition and removal
    - Configurable ticket release and retrieval rates

- *System Configuration*
    - Configurable total ticket count
    - Adjustable ticket release rate
    - Customizable customer retrieval rate
    - Maximum ticket pool capacity control

- *User Management*
    - Customer registration and tracking
    - Vendor registration and management
    - Unique ID generation for users

## API Documentation

For detailed API documentation, please refer to API.md in the project root directory.

## Setup and Configuration

1. *Database Configuration*
   Update application.properties with your PostgreSQL credentials:
   properties
   spring.datasource.url=jdbc:postgresql://localhost:5334/event-tracking-system
   spring.datasource.username=your_username
   spring.datasource.password=your_password

2. *System Requirements*
    - Java 17 or higher
    - PostgreSQL 12 or higher
    - Maven 3.6 or higher

3. *Build and Run*
   bash
   mvn clean install
   mvn spring-boot:run

## Concurrency Features

- Thread-safe ticket pool operations using synchronized methods
- Wait-notify mechanism for handling pool capacity limits
- Atomic operations for ticket additions and removals
- Configurable sleep durations for VIP and regular customers

## Error Handling

- Synchronized pool access prevents race conditions
- Thread interruption handling
- Proper resource cleanup
- Validation for customer and vendor operations

## Development Notes

- The system uses JPA repositories for data persistence
- Lombok annotations reduce boilerplate code
- Spring's dependency injection manages component lifecycle
- Thread safety is ensured through synchronized methods

## Future Improvements

1. Add authentication and authorization
2. Implement ticket reservation timeout
3. Add transaction management
4. Enhance error handling and logging
5. Add metrics and monitoring
6. Implement caching layer

# How to run

### To run the springboot application
1. mvn clean install
2. mvn spring-boot:run

### To run the database
1. docker ps -> to check running containers
2. docker exec -it postgres psql -U lasanyaiit -> postgres is the container name; lasanyaiit is the username
#### once the above command is success
3. \l -> to check existing databases
4. \c event-tracking-system -> to connect to the database; event-tracking-system is the database name