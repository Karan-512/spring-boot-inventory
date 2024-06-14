FROM openjdk:17-jdk-slim
# Copy your Spring Boot application JAR file
COPY target/task1-0.0.1-SNAPSHOT.jar /app/app.jar

# Set the working directory
WORKDIR /app

# Expose the port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]



