# Ecommerce study

Practicing Java and DDD

## Setup

Clone the repository. To setup the database used by this project, use:

```aidl
docker-compose up -d
```

With database working, you can build environment using flyway:
 ```aidl
./gradlew flywayMigrate
```

Finally, you can load a server using:
```aidl
./gradlew bootRun
```

## How many things we need to care

This is a nice project for me. I am practicing DDD inside a hexagonal architecture, using Java as main language and
Spring boot as the service framework.

- hexagonal architecture expressed trough folders
- DDD started with event storming technique
- spring boot project started from initializr
- two modules to encapsulate external dependencies and keep core pure
- docker for postgres database dev creation
- flyway to database migration
- jsonb to store cart items on database
- sonarqube on multiple module project
- github actions to run checks and sonarqube
- coverage with jacoco
- API responses following [rfc7807](https://datatracker.ietf.org/doc/html/rfc7807) using `@ControllerAdvice` and `Problem`
- profiles to deal with default and prod environment
- action configuration to build and push docker image to docker hub
- actuator to health check endpoints

