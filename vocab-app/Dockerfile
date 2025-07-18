FROM registry.cn-hangzhou.aliyuncs.com/ljava/arm64v8-openjdk:17-jdk-slim

WORKDIR /app

COPY target/vocab-app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
