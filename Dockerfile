FROM eclipse-temurin:21
WORKDIR /app
COPY target/quarkus-app/ /app/
CMD ["java", "-jar", "/app/quarkus-run.jar"]
