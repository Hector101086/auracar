FROM openjdk:18-alpine
ADD target/auracar-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
