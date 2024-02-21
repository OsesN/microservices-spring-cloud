FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/sales-service-0.0.1.jar
COPY ${JAR_FILE} sales_app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","sales_app.jar"]
