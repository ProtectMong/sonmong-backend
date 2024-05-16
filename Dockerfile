FROM amazoncorretto:17

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ARG SERVER_PROFILE
ENV SERVER_PROFILE=$SERVER_PROFILE

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SERVER_PROFILE}", "app.jar"]