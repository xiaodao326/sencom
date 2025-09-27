# 使用 JDK 17 作为基础镜像（根据你项目的 JDK 版本修改）
FROM openjdk:17-jdk-slim

# 拷贝 jar 包到容器中并命名为 app.jar
COPY target/sencom-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口（和 application.yml/application.properties 里配置的 server.port 一致）
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "/app.jar"]

#docker build -t sencom:1.1 .
#docker run -d -p 8080:8080 --name sencom sencom:1.1