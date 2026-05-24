FROM maven:3-eclipse-temurin-21-alpine

COPY ./ /opt/project
WORKDIR /opt/project

ENV SUITE=default-suite.xml

CMD ["sh", "-c", "mvn clean test -Denv=default -Dsuite=$SUITE"]