FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copy the jar produced by Maven
COPY target/backend-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
