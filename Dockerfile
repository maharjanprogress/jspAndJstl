FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the JAR file
COPY target/jspAndJstl-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default for Spring Boot is 8080)
EXPOSE 9090


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]