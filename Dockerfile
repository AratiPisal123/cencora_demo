FROM eclipse-temurin:21
WORKDIR /app
COPY target/*-runner.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
