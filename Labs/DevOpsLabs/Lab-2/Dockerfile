FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package spring-boot:repackage -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
# Беремо саме repackaged JAR
COPY --from=builder /build/target/aitest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]