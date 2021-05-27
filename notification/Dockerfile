FROM openjdk:14-jdk-alpine
COPY ./target/notification-luizalabs*.jar notification-luizalabs.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "notification-luizalabs.jar"]