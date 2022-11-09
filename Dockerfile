FROM openjdk:17-jdk-slim
COPY target/app-*.jar substring-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/substring-app.jar"]