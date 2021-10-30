# 基础镜像
FROM java:8
# 暴露端口
EXPOSE 1111
# 引用plugin中配置的 JAR_FILE 文件
ARG JAR_FILE=cdh-0.1.jar
# 打包jar去向
ADD ${JAR_FILE} /opt/${JAR_FILE}
# 启动命令
ENTRYPOINT ["java", "-jar", "/opt/${JAR_FILE}"]
