FROM java:openjdk-8-jre-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} mscrm.jar
#RUN bash -c 'touch /mscrm.jar'
ADD target/flink-api-server.jar flink-api-server.jar
# 指定容器内的时区
RUN echo "Asia/Shanghai" > /etc/timezone
#RUN apk --update add ttf-dejavu && apk add --no-cache tzdata && echo "Asia/Shanghai" > /etc/timezone && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#RUN rm -rf /var/cache/apk/*
ENV CE=$CE
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8501
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /flink-api-server.jar --spring.cloud.config.profile=$CE"]