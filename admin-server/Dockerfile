FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/admin-server.jar admin-server.jar
EXPOSE 7086
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin-server.jar"]