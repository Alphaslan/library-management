FROM java:8
EXPOSE 8083
ADD target/spring-docker-maven.jar spring-docker-maven.jar
ENTRYPOINT ["java","-jar","spring-docker-maven.jar"]