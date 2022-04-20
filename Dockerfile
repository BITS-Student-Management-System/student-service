FROM adoptopenjdk/openjdk14:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} student-service.jar
ENTRYPOINT ["java","-jar","/student-service.jar"]
EXPOSE 9002