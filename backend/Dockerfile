FROM alpine/java:21-jdk
WORKDIR /app
COPY ./target/*.jar tmdb.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tmdb.jar"]