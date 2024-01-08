FROM openjdk:19-jdk-alpine3.15
WORKDIR /app
COPY target/todoapp.jar /app/todoapp.jar
EXPOSE 8080
ENTRYPOINT [ "java" , "-jar" , "todoapp.jar" ]