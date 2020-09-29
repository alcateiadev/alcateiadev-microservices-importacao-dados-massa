FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD /target/processar-dados.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]