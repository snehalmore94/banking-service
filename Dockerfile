FROM openjdk:8
ADD target/banking-service.jar banking-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/banking-service.jar"]