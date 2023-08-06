FROM openjdk:17-jdk-alpine
COPY /app.jar istateca_back.jar
ENTRYPOINT ["java", "-jar", "istateca_back.jar"]
