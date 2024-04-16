FROM openjdk:17-jdk-alpine
COPY target/BigData_Datenaufbereitung-0.1.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]
EXPOSE 5555