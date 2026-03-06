FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
WORKDIR /app
COPY build/libs/devops_demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
