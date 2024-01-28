FROM amazoncorretto:17

WORKDIR /opt/app

COPY target/ms*.jar app.jar

USER nobody

CMD [ "java", "-jar", "app.jar" ]
