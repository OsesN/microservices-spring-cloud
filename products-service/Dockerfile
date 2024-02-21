FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/products-service-0.0.1.jar
COPY ${JAR_FILE} products_app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","products_app.jar"]
