FROM maven:3.5.2-jdk-8-alpine

VOLUME /tmp

COPY ./src/ /usr/src/gs-spring-boot/src/
COPY ./pom.xml /usr/src/gs-spring-boot/pom.xml

#ENV JAVA_OPTS=""

RUN cd /usr/src/gs-spring-boot && mvn -Dmaven.test.skip=true package

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /usr/src/gs-spring-boot/target/gs-spring-boot-0.1.0.jar

