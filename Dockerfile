# Base image with Java
FROM openjdk:17-jdk-slim

# Working directory in container
WORKDIR /app

# Copy jar file into container
COPY target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]

