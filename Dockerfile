FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/sisgeda-0.0.1.jar
COPY ${JAR_FILE} app_sisgeda.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_sisgeda.jar"]