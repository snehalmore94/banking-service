# Banking Service

## Introduction

Banking Service is a streamlined application built on Spring Boot, Java, and Maven. It creates accounts, with APIs documented via Swagger, containerized with Docker, and data stored using the H2 database, while GitHub handles version control and CI/CD pipelines with GitHub Actions.


## Technologies Used
- **Java 17**: For writing application code.
- **Spring Boot**: Framework for creating stand-alone, production-grade Spring based Applications.
- **Maven**: For dependency management and project build.
- **Docker**: For containerizing the application and ensuring consistent environments.
- **Swagger**: For API documentation and testing.
- **H2 Database**: An in-memory database used for development and testing.
- **Git**: For version control.
- **GitHub Actions**: For continuous integration and deployment pipeline.
- **JaCoCo**: For measuring and reporting code coverage to ensure quality code.

## Features
- **Account Creation**
- **Customer Information**
- **API Documentation**
- **In-Memory Data Persistence**
- **Containerization Support**
- **Automated CI/CD**
- **Code Quality Assurance**

## Prerequisites

- Java 17
- Spring Boot 3.2.5
- Maven
- Docker
- Git

## Installation

### Clone the Repository

```bash
git clone https://github.com/snehalmore94/banking-service.git

cd banking-service
```

### Build with Maven
The next step is to build the project using Maven. This will compile the source code, run tests, and package the application. Execute the following command in the root directory of the project:
```bash
./mvn clean install
```

### Running the Application
Once the build is successful, start the application from CreateAccountApplication. This class should be in the src/main/java/com/assignment/bankingservice/CurrentAccountApplication.java

### API Endpoints
The application defines the following RESTful endpoints:
* Create Account: POST /api/accounts
  - Creates a new account for an existing customer with an initial credit amount.
  - Parameters: customerId (Long), initialCredit (BigDecimal)
* Get Customer Information: GET /api/accounts/customer-info
  - Retrieves detailed information about a customer, including their transactions, based on the customer ID.
  - Parameters: customerId (Long)

### API Documentation
The APIs are documented using Swagger and can be accessed at:
http://localhost:8080/swagger-ui.html

This provides a detailed overview of all the APIs, including request/response formats and the ability to test the APIs directly from the browser.

### H2 Database
The h2 database can be accessed at:
http://localhost:8080/h2-console


## Version Control
This project uses Git for version control, hosted on GitHub. It allows for collaborative development and version tracking.
The primary branch for development and contributions is `main`
 
### CI/CD with GitHub Actions
GitHub Actions is used for continuous integration and deployment. 

#### Pipeline Process
The GitHub Actions pipeline is configured to perform the following actions automatically:
1. **Check out the repository**
2. **Set up JDK 17**
3. **Build with Maven**
4. **Build and push Docker Image**: Creates a Docker image from the `Dockerfile` and pushes it to Docker Hub under the `snehalmore94/banking-service` repository with the `latest` tag.
5. **Generate JaCoCo badge**
6. **Log coverage percentages**
7. **Upload JaCoCo coverage report**

### Triggering the Pipeline
The pipeline is triggered by any push to the master branch. 


 
This README provides a comprehensive overview of your project, including how to get started, use Docker, and access API documentation. Specific details like Docker Hub username is necessary to match your project's configuration.


