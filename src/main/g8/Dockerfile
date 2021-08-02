FROM giftig/sbt:2.13.6

WORKDIR /build
COPY . /build

RUN ["scripts/build.sh"]
RUN ["/bin/bash", "mv \$(find /build -iname 'dde-feedbank-$sport;format="lowercase"$-service*.jar') /build/app.jar"]


FROM openjdk:11

WORKDIR /usr/src/app
VOLUME /usr/src/app/conf
EXPOSE 8080

COPY --from=0 /build/app.jar /usr/src/app/app.jar
COPY docker/app.conf /usr/src/app/conf/app.conf
COPY docker/logback.xml /usr/src/app/conf/logback.xml

VOLUME /usr/src/app/conf

CMD ["java", "-Dapp.conf=conf/app.conf", "-Dlogback.configurationFile=conf/logback.xml", "-jar", "app.jar"]
