FROM alpine/java:21-jdk
WORKDIR /app
COPY ./target/*.jar movie-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "movie-app.jar"]