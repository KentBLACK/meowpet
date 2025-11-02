FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /opt/app
COPY mvnw pom.xml ./
COPY ./src ./src
RUN  mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=build /opt/app/target/*.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]