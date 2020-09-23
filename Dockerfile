FROM java:8-jdk-alpine

COPY ./target/IoBuilders.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch IoBuilders.jar'

ENTRYPOINT ["java","-jar","IoBuilders.jar"]  