FROM openjdk:11-jdk
RUN mkdir data
RUN mkdir /data/files
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
