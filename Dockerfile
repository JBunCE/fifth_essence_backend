FROM amazoncorretto:17-alpine-jdk
ADD target/fifth.jar fifth.jar
ENTRYPOINT ["java", "-jar", "fifth.jar"]