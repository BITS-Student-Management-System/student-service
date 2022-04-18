#define base docker image
FROM openjdk:8-jdk-alpine
LABEL maintainer="roopika.srinivas"
ADD target/student-service-0.0.1-SNAPSHOT.jar student-service.jar
ENTRYPOINT ["java","-jar","student-service.jar"]
