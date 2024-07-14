# Use OpenJDK 17 Alpine as base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy your application files to the container
COPY ./ ./

EXPOSE 8080

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "api.jar"]
