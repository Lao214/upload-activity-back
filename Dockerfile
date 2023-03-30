FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/activity.jar activity.jar
ENTRYPOINT ["java","-jar","/activity.jar","&"]