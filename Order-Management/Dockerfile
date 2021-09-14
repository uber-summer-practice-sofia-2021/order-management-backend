FROM maven:3.8.2-adoptopenjdk-15

ADD . /tmp
WORKDIR "/tmp"
RUN mvn package
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/Order-Management-0.0.1-SNAPSHOT.jar"]