FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR fake-store-app
COPY --from=build target/*.jar fake-store-app.jar
ENTRYPOINT ["java", "-jar", "fake-store-app.jar"]