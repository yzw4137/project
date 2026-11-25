# 通用 Java 17 Spring Boot 镜像构建文件
# 通过 docker build --build-arg JAR_FILE=xxx.jar 指定 jar 包路径
FROM eclipse-temurin:17-jre-alpine

# 设置时区为东八区，避免容器内时间偏差
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 接受构建参数：jar 包相对路径（相对于 build context）
ARG JAR_FILE

# 暴露端口（实际端口由各服务自己的 application.yml 决定，这里只是文档作用）
EXPOSE 8082 8083 8084 8088

# 拷贝 jar 到容器内并重命名为 app.jar
COPY ${JAR_FILE} app.jar

# JVM 参数：容器化场景下调整内存，避免默认 max 物理内存的 1/4 过大
# -XX:+UseContainerSupport 让 JVM 感知 cgroup 内存限制（JDK 10+ 默认开启）
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "/app.jar"]
