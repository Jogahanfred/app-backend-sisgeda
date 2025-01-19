# Etapa de construcción para compilar el JAR (si es necesario)
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa final para ejecutar el JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app-backend-sisgeda-0.0.1.jar app_sisgeda.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_sisgeda.jar"]
