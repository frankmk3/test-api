FROM openjdk:21

ADD  api.jar  /home/app.jar

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /home/app.jar $APP_OPTS