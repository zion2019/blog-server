FROM openjdk:8-jdk-alpine

VOLUME /logs/resource:/log/resource

ADD *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]