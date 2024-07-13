# Use OpenJDK 17 Alpine as base image
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy your application files to the container
COPY . .

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "dinujayacarsaleapi.jar"]
