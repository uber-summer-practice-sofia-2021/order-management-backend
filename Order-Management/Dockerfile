FROM maven:3.8.2-adoptopenjdk-15

RUN ./mvnw package
ARG JAR_FILE=target/Order-Management-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
RUN docker build -t radinan/order-management .

EXPOSE 8080
#RUN docker run radinan/order-management
ENTRYPOINT ["java","-jar","/app.jar"]