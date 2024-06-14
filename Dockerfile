FROM ubuntu:latest AS build
# Set working directory
WORKDIR /app

# Copy the Maven project and pom.xml
COPY . .

# Build the application without running tests
RUN ./mvnw clean package -DskipTests

# Production stage
FROM ubuntu:latest

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/task1-0.0.1-SNAPSHOT.jar /app/task1-0.0.1-SNAPSHOT.jar

# Expose port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "task1-0.0.1-SNAPSHOT.jar"]



