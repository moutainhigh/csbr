FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/order-business-server.jar order-business-server.jar
ENV CE=$CE
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8082
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /order-business-server.jar --spring.cloud.config.profile=$CE"]