# 基础镜像
FROM java:8
# 暴露端口
EXPOSE 1111
# 引用plugin中配置的 JAR_FILE 文件
#ARG JAR_FILE=cdh-0.1.jar
ARG ARG_VERSION=0.1
ENV JAR_NAME=cdh
ENV JAR_PKG=${JAR_NAME}-${ARG_VERSION}.jar
# 打包jar去向
ADD ${JAR_PKG} /opt/data/${JAR_PKG}
# 启动命令
ENTRYPOINT java -jar /opt/data/${JAR_PKG}

