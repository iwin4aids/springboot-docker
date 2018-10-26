FROM 192.168.144.132/base/alpine-oraclejdk8:latest
MAINTAINER jiangkun
COPY target/springboot*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]