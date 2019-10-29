FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/config-server.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-server.jar"]