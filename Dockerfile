FROM eclipse-temurin:21
WORKDIR /app
COPY target/quarkus-app/ .
CMD ["java", "-jar", "quarkus-run.jar"]
