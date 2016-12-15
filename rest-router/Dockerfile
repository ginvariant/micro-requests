FROM openjdk:8-jre-alpine

ENV JAVA_APP_JAR rest-router-0.0.1-SNAPSHOT-fat.jar

EXPOSE 8080

ADD target/$JAVA_APP_JAR /app/
RUN chmod 777 /app/

RUN wget https://github.com/jwilder/dockerize/releases/download/v0.3.0/dockerize-alpine-linux-amd64-v0.3.0.tar.gz
RUN tar -C /usr/local/bin -xvzf dockerize-linux-amd64-v0.0.1.tar.gz

ADD dockerize /usr/local/bin/dockerize

# For when we have logs...
# CMD dockerize -stdout /var/log/rest-router/.log -stderr /var/log/rest-router/error.log -wait tcp://mongo:27017 java -jar $JAVA_APP_JAR 

WORKDIR /app/
CMD dockerize -wait tcp://mongo:27017 java -jar $JAVA_APP_JAR