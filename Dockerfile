# Use Java 17 image
FROM openjdk:17-jdk-slim
#eclipse-temurin:17-jre

# Set working directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/VotingSystem-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]