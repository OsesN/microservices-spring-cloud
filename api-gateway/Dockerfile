FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/api-gateway-0.0.1.jar
COPY ${JAR_FILE} gateway_app.jar
EXPOSE 444
ENTRYPOINT ["java","-jar","gateway_app.jar"]