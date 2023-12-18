FROM openjdk:18-jdk-alpine3.13

EXPOSE 8080

ADD target/netology-authorization-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]