FROM openjdk:11

RUN curl -fsSL https://deb.nodesource.com/setup_14.x | bash -
RUN apt-get install -y nodejs
RUN npm install -g nodemon

COPY . .

RUN ./mvnw --settings pom.xml clean install

CMD [ "./mvnw", "--settings", "pom.xml", "spring-boot:run" ]

