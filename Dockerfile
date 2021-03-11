FROM openjdk:8-jre-alpine
EXPOSE 8080
WORKDIR /app
COPY target/studentreport-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","studentreport-0.0.1-SNAPSHOT.jar"]

