FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/ms-common-admin-platform-auth-service.jar ms-common-admin-platform-auth-service.jar
# 指定容器内的时区
RUN echo "Asia/Shanghai" > /etc/timezone
ENV CE=$CE
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 9102
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /ms-common-admin-platform-auth-service.jar --spring.cloud.config.profile=$CE"]