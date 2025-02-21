# gym-crm-system

The Gym CRM System is a customer relationship management application. It helps manage trainers, trainees, training sessions, and training types efficiently. Built using Spring Core (without Spring Boot), the system uses Spring Context for dependency injection and annotation-based configuration.

## Features

- CRUD operations for **Trainers**, **Trainees**, **Training Types**, and **Training Sessions**.

- Data loading mechanism for all entities using a file-based approach.

- Uses **Spring Context** for dependency injection and bean management.

## Technologies Used

- Java 21

- Spring Context (for @Configuration, @Component, @Bean, @Repository annotations)

- SLF4J & Logback (for logging)

## Key Classes
- `CustomBeanPostProcessor`

Handles bean post-processing and assigns data loaders based on StorageLoaderType.
- `FileReader` (Utility Class)

Reads data from files and maps them to entity objects.
- `DataLoader` (Data Loader Implementation)

Loads entity data from a file into in-memory storage.