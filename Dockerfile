
# WORKDIR /app

# COPY task1-0.0.1-SNAPSHOT.jar /app/task1-0.0.1-SNAPSHOT.jar

# EXPOSE 8080

# CMD ["java", "-jar", "task1-0.0.1-SNAPSHOT.jar"]



# Stage 1: Build stage
FROM adoptopenjdk/openjdk21:jdk-21.0.3_5-slim AS build

WORKDIR /build

# Copy the Maven project and pom.xml
COPY . .

# Build the application without running tests
RUN ./mvnw clean package -DskipTests

# Production stage
FROM adoptopenjdk/openjdk21:jdk-21.0.3_5-slim

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /build/target/task1-0.0.1-SNAPSHOT.jar /app/task1-0.0.1-SNAPSHOT.jar

# Expose port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "task1-0.0.1-SNAPSHOT.jar"]
