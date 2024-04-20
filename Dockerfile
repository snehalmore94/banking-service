FROM openjdk:8
ADD target/create-account-docker.jar create-account-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "create-account-docker create-account-docker.jar"]