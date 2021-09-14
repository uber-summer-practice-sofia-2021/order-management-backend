FROM adoptopenjdk/openjdk15:ubi
ARG JAR_FILE=target/Order-Management-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]