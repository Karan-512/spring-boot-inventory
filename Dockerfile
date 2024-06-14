
# WORKDIR /app

# COPY task1-0.0.1-SNAPSHOT.jar /app/task1-0.0.1-SNAPSHOT.jar

# EXPOSE 8080

# CMD ["java", "-jar", "task1-0.0.1-SNAPSHOT.jar"]



# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /build

# Copy only the pom.xml to resolve dependencies
COPY pom.xml .

# Download dependencies
RUN mvn -B dependency:go-offline

# Copy the source code and build the application
COPY src src
RUN mvn -B clean package -DskipTests

# Stage 2: Production stage
FROM ubuntu:latest

WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /build/target/task1-0.0.1-SNAPSHOT.jar /app/task1-0.0.1-SNAPSHOT.jar

# Expose port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "task1-0.0.1-SNAPSHOT.jar"]
