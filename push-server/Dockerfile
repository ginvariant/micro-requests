FROM ubuntu:16.04

ENV JAVA_APP_JAR push-server-0.0.1-SNAPSHOT-fat.jar

EXPOSE 8080

ADD target/$JAVA_APP_JAR /app/
RUN chmod 777 /app/

# if you have problems with the apt-get update
# add DOCKER_OPTS="-H tcp://127.0.0.1:2375 --dns x.x.x.x"

RUN  apt-get update \
  && apt-get install -y wget apt-utils \
  && rm -rf /var/lib/apt/lists/* \
  && wget https://github.com/jwilder/dockerize/releases/download/v0.3.0/dockerize-alpine-linux-amd64-v0.3.0.tar.gz \  
  && tar -C /usr/local/bin -xvzf dockerize-alpine-linux-amd64-v0.3.0.tar.gz

# For when we have logs...
# CMD dockerize -stdout /var/log/push-server/.log -stderr /var/log/push-server/error.log -wait tcp://mongo:27017 java -jar $JAVA_APP_JAR 

WORKDIR /app/

CMD /usr/local/bin/dockerize -wait tcp://mongo:27017 java -jar $JAVA_APP_JAR