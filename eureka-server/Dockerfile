FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/eureka-server.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server.jar"]