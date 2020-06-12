FROM openjdk:8-jre
MAINTAINER Haystack <haystack@expedia.com>

ENV APP_NAME haystack-opentelemetry-example
ENV APP_HOME /app/bin

COPY target/${APP_NAME}.jar ${APP_HOME}/
COPY start-app.sh ${APP_HOME}/

WORKDIR ${APP_HOME}

EXPOSE 9090

ENTRYPOINT ["./start-app.sh"]