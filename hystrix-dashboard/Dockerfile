FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/hystrix-dashboard.jar hystrix-dashboard.jar
EXPOSE 8083
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hystrix-dashboard.jar"]