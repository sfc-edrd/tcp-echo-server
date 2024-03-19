FROM amazoncorretto:18-alpine3.15-jdk

WORKDIR /app

COPY tcp-echo-server.jar ./tcp-echo-server.jar

ENTRYPOINT ["java","-jar","./tcp-echo-server.jar"]