FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/zuul-server.jar zuul-server.jar
EXPOSE 8085
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/zuul-server.jar"]