FROM openjdk:21-jdk-slim AS builder

WORKDIR /src

COPY mvnw.cmd .
COPY pom.xml .
COPY src src
COPY .mvn .mvn
COPY mvnw .

RUN chmod +x ./mvnw && \
./mvnw package -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /src/target/workshop18-0.0.1-SNAPSHOT.jar app.jar

ENV CURRCONV_APIKEY=abc123 PORT=8080

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar