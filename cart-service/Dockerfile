FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/cart-service-0.0.1.jar
COPY ${JAR_FILE} cart_app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","cart_app.jar"]