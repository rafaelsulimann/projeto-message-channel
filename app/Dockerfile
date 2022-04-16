FROM openjdk:19-slim-bullseye
EXPOSE 8080
ADD target/projeto-weon-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.datasource.url=${DATABASE_URL}", "-jar", "/app.jar"]