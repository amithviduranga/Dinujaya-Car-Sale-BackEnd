# Use OpenJDK 17 Alpine as base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy your application files to the container
COPY target/api-0.0.1-SNAPSHOT.jar DCSApi.jar

EXPOSE 8080

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "DCSApi.jar"]
