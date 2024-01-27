FROM maven:3-amazoncorretto-17 AS build

WORKDIR /opt/app

COPY src ./src
COPY pom.xml .

RUN mvn clean package

FROM amazoncorretto:17

WORKDIR /opt/app

COPY --from=build /opt/app/target/msPagamentoTechChallenge*.jar app.jar

USER nobody

CMD [ "java", "-jar", "app.jar" ]