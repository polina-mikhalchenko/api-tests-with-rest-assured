FROM alpine:latest

EXPOSE 35725

ENV ALLURE_VERSION 2.24.1

RUN mkdir /allure
RUN mkdir /allure-config

COPY src /opt/pet-store/src/
COPY pom.xml /opt/pet-store

RUN mkdir /opt/pet-store/allure-results
RUN mkdir /opt/pet-store/allure-report


RUN  apk update \
  && apk add --update openjdk17 maven mc \
  && rm -rf /var/cache/apk/*

RUN wget https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/$ALLURE_VERSION/allure-commandline-$ALLURE_VERSION.tgz
RUN tar -zxf allure-commandline-$ALLURE_VERSION.tgz
RUN mv ./allure-$ALLURE_VERSION/* /allure/
RUN rm allure-commandline-$ALLURE_VERSION.tgz

ENV PATH="/allure/bin:${PATH}"
ENV ALLURE_CONFIG="/allure-config/allure.properties"

CMD  mvn -f /opt/pet-store/pom.xml -fn clean test \
    && allure serve /opt/pet-store/target/allure-results -p 35725

