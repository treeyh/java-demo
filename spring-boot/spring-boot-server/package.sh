#!/bin/bash

#取当前目录
BASE_PATH=`cd "$(dirname "$0")"; pwd`

cd $BASE_PATH

# 打包命令
mvn clean package -Denv=dev

# 打压缩包
cd $BASE_PATH/spring-boot-web/target/springboot-web/
tar zcf $BASE_PATH/spring-boot-web/target/spring-boot-web.tar.gz *

cd $BASE_PATH
# 重命名web发布目录
mv $BASE_PATH/spring-boot-web/target/spring-boot-web/ $BASE_PATH/spring-boot-web/target/dist/


echo "服务接口包路径："
echo "$BASE_PATH/spring-boot-web/target/spring-boot-web.tar.gz"
echo "OK"