FROM openjdk:16-jdk-alpine3.13
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY ./application/build/libs/application-0.1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]