# Use OpenJDK 17 Alpine as base image
FROM eclips-temurin:17-jdk-alphine

# Set the working directory inside the container
WORKDIR /app

# Copy your application files to the container
COPY  target/dinujaya-car-sale-api.jar dinujaya-car-sale-api.jar

EXPOSE 8080

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "dinujaya-car-sale-api.jar"]
