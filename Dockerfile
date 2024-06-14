FROM ubuntu:latest

WORKDIR /app

COPY task1-0.0.1-SNAPSHOT.jar /app/task1-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "task1-0.0.1-SNAPSHOT.jar"]