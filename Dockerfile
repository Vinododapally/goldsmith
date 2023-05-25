FROM openjdk:8-jdk-alpine
COPY build/libs/goldsmith.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]