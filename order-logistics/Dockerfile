FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/order-logistics-server.jar order-logistics-server.jar
ENV CE=$CE
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8083
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /order-logistics-server.jar --spring.cloud.config.profile=$CE"]