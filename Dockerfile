FROM openjdk:8-jre-alpine
MAINTAINER jiangkun
COPY target/springboot*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]