FROM maven:3.8.3-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src/
RUN mvn clean package
FROM openjdk:20-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
