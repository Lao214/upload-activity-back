FROM openjdk:8-jdk
VOLUME /tmp
COPY ./target/activity.jar activity.jar
ENTRYPOINT ["java","-jar","/activity.jar","&"]