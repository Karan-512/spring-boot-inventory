FROM adoptopenjdk/openjdk21:jdk-21.0.0.10_1-alpine

WORKDIR /app

COPY target/my-spring-boot-app.jar /app

EXPOSE 8080

CMD ["java", "-jar", "my-spring-boot-app.jar"]