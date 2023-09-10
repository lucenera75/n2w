FROM maven:3.8.3-openjdk-17-slim
COPY pom.xml .
COPY src ./src/
RUN mvn clean package
COPY ./target/*.jar n2w.jar
EXPOSE 8080
CMD ["java", "-jar", "n2w.jar"]
