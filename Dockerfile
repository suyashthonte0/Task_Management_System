FROM openjdk:11.0.13
EXPOSE 8080
ADD target/tasks-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]