FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD /target/leitor-vendedor.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]