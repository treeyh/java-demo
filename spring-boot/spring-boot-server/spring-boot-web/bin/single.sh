#!/bin/bash

APP_NAME="${pro.name}"

#取当前目录
BASE_PATH=`cd "$(dirname "$0")"; pwd`

SERVER_ENVIROMENT=$RUN_ENV

LOG_PATH="/data0/logs/${APP_NAME}"
PID_PATH="/data0/webroot/${APP_NAME}"

Xmx="1024m"

if [ "${SERVER_ENVIROMENT}"x = "self"x ]
then
    Xmx="1024m"
    LOG_PATH="$BASE_PATH/logs"
    PID_PATH="$BASE_PATH"
elif [ "${SERVER_ENVIROMENT}"x = "dev"x ]
then
    Xmx="512m"
elif [ "${SERVER_ENVIROMENT}"x = "test"x ]
then
    Xmx="512m"
elif [ "${SERVER_ENVIROMENT}"x = "staging"x ]
then
    Xmx="256m"
elif [ "${SERVER_ENVIROMENT}"x = "production"x ]
then
    Xmx="4096m"
else
    echo "Environment variables RUN_ENV not found."
    exit -1
fi


if [ ! -d "$LOG_PATH" ]; then
	mkdir -p $LOG_PATH
fi
if [ ! -d "$PID_PATH" ]; then
	mkdir -p $PID_PATH
fi

#取外部数据
XMX_VAR=${Xmx}
#去掉结尾的G
XMX_VAR=${XMX_VAR%%m}
#最大堆大小数值*3/4,m
XMX_V=`expr $XMX_VAR \* 3 / 4`
#初始堆大小数值*3/4,m
XMS_V=`expr $XMX_VAR \* 3 / 4`
#年轻代大小数值*3/8,m
XMN_V=`expr $XMX_VAR \* 3 / 8`



# 设置java启动参数
# Xmx:JVM 最大允许分配的堆内存，按需分配
# Xms:JVM 初始分配的堆内存，与Xmx设置相同
# Xmn:JVM 设置年轻代大小，整个堆大小 = 年轻代大小 + 年老代大小 + 持久代大小，Sun 官方推荐配置为整个堆的 3/8
# Xss:设置每个线程的堆栈大小
# XX:MetaspaceSize:JVM 初始分配的元数据内存，1.8
# XX:MaxMetaspaceSize:JVM 最大分配的元数据内存，1.8
MEM_OPTS=" -Xmx${XMX_V}m -Xms${XMS_V}m -Xmn${XMN_V}m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m"

# XX:+UseConcMarkSweepGC:设置年老代为CMS收集
# XX:CMSInitiatingOccupancyFraction:表示年老代空间到 70% 时就开始执行 CMS
# XX:+UseCMSInitiatingOccupancyOnly:使用手动定义初始化定义开始 CMS 收集,禁止 hostspot 自行触发 CMS GC
GC_OPTS="-XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseCMSInitiatingOccupancyOnly"
# XX:+ExplicitGCInvokesConcurrent:System.gc() 使用 CMS 算法
GC_OPTS="$GC_OPTS -XX:+ExplicitGCInvokesConcurrent"
# CMS 中的下列阶段并发执行
GC_OPTS="$GC_OPTS -XX:+ParallelRefProcEnabled -XX:+CMSParallelInitialMarkEnabled"
# XX:MaxTenuringThreshold:对象如果在Survivor 区移动 3 次还没有被回收就放入年老代，加快 YGC 速度
GC_OPTS="$GC_OPTS -XX:MaxTenuringThreshold=3"

# Xloggc:把相关日志信息记录到文件以便分析.
# XX:+PrintGCDetails:打印gc详情
# XX:+PrintGCDateStamps:打印可读的日期
# XX:+PrintPromotionFailure:打开了就知道是多大的新生代对象晋升到老生代失败从而引发 Full GC 时的
# XX:+PrintGCApplicationStoppedTime:打印清晰的完整的 GC 停顿时间外，还可以打印其他的 JVM 停顿时间
# verbose:gc:输出每次gc的相关情况
GCLOG_OPTS="-Xloggc:${LOG_PATH}/gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintPromotionFailure -XX:+PrintGCApplicationStoppedTime -verbose:gc"
# 打印 GC 前后的各代大小
#GCLOG_OPTS="$GCLOG_OPTS -XX:+PrintHeapAtGC"
# 打印存活区每段年龄的大小
#GCLOG_OPTS="$GCLOG_OPTS -XX:+PrintTenuringDistribution"
# 如果发生晋升失败，观察老生代的碎片
#GCLOG_OPTS="$GCLOG_OPTS -XX:+UnlockDiagnosticVMOptions -XX:PrintFLSStatistics=2"
# 打印安全点日志，找出 GC 日志里非 GC 的停顿的原因
#GCLOG_OPTS="$GCLOG_OPTS -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=1 -XX:+UnlockDiagnosticVMOptions -XX:-DisplayVMOutput -XX:+LogVMOutput -XX:LogFile=${LOG_PATH}/vm.log"

# XX:-UseBiasedLocking:取消偏向锁
# XX:AutoBoxCacheMax:多少以内的int自动装箱
# Djava.security.egd:SecureRandom 生成加速
OPTIMIZE_OPTS="-XX:-UseBiasedLocking -XX:AutoBoxCacheMax=20000 -Djava.security.egd=file:/dev/./urandom"

# XX:+PrintCommandLineFlags:将每次启动的参数输出到 stdout
# XX:-OmitStackTraceInFastThrow:禁止对相同的异常进行输出优化
# XX:ErrorFile:JVM crash 时，hotspot 会生成一个 error 文件，提供 JVM 状态信息的细节
SHOOTING_OPTS="-XX:+PrintCommandLineFlags -XX:-OmitStackTraceInFastThrow -XX:ErrorFile=${LOG_PATH}/hs_err_sys.log"
# OOM 时进行 HeapDump，但此时会产生较高的连续 IO，如果是容器环境，有可能会影响他的容器
SHOOTING_OPTS="$SHOOTING_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_PATH}/gc.hprof"

JMX_OPTS=""
#开放 JMX 本地访问，设定端口号
#JMX_OPTS="$JMX_OPTS -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=7001 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

# Djava.net.preferIPv4Stack:开启ipv4
# Dfile.encoding:设置文件编码
OTHER_OPTS="-Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"


#设置java运行参数
DEFAULT_JAVA_OPTS=" -server $MEM_OPTS $GC_OPTS $GCLOG_OPTS $OPTIMIZE_OPTS $SHOOTING_OPTS $JMX_OPTS $OTHER_OPTS "

#定义变量:
APP_PATH=${APP_PATH:-`dirname "$BASE_PATH"`}
CLASS_PATH=${CLASS_PATH:-$APP_PATH/config:$APP_PATH/lib/*}
JAVA_OPTS=${JAVA_OPTS:-$DEFAULT_JAVA_OPTS}
DEFAULT_JAR=$(find $APP_PATH/lib/ -name *.jar)



# SERVER_ACCESSABLE_IP=$SERVER_ACCESSABLE_IP
# if [ -z "$SERVER_ACCESSABLE_IP" ]; then
# 	SERVER_ACCESSABLE_IP=$(ifconfig eth0 |grep 'inet add'|awk -F ":" '{print $2}'|awk '{print $1}')
# fi
# SERVER_ACCESSABLE_PORT=$SERVER_ACCESSABLE_PORT
# if [ -z "$SERVER_ACCESSABLE_PORT" ]; then
# 	SERVER_ACCESSABLE_PORT=10000
# fi
# export SERVER_ACCESSABLE_IP=${SERVER_ACCESSABLE_IP}
# export SERVER_ACCESSABLE_PORT=${SERVER_ACCESSABLE_PORT}


cd $APP_PATH
# ulimit -HSn ${ULIMIT}

exist(){
    if test $( pgrep -f "${APP_NAME}-app" | wc -l ) -eq 0
    then
        return 1
    else
        return 0
    fi
}

start(){
    echo "APP_NAME=$APP_NAME"
    echo "XMX_VAR=$XMX_VAR"
    echo "SERVER_ENVIROMENT=$SERVER_ENVIROMENT"
    echo "APP_PATH=$APP_PATH"
    echo "BASE_PATH=$BASE_PATH"
    echo "DEFAULT_JAR=$DEFAULT_JAR"
    echo "CLASS_PATH=$CLASS_PATH"

    if exist; then
        echo "$APP_NAME is already running."
        exit 1
    else
        cd $APP_PATH
        nohup java ${JAVA_OPTS} -cp ${CLASS_PATH} -jar ${DEFAULT_JAR} --spring.profiles.active=${SERVER_ENVIROMENT} ${APP_NAME}-app >$LOG_PATH/stdout.log 2>&1 &
        sleep 3s
        runningPID=`pgrep -f "${APP_NAME}-app"`
        echo $runningPID > $PID_PATH/pid
        echo "$APP_NAME is started. pid:$runningPID"
    fi
}

stop(){
    runningPID=`pgrep -f "${APP_NAME}-app"`
    if [ "$runningPID" ]; then
        echo "$APP_NAME pid: $runningPID"
        count=0
        kwait=5
        echo "$APP_NAME is stopping, please wait..."
        kill -15 $runningPID
        until [ `ps --pid $runningPID 2> /dev/null | grep -c $runningPID 2> /dev/null` -eq '0' ] || [ $count -gt $kwait ]
        do
            sleep 1
            let count=$count+1;
        done

        if [ $count -gt $kwait ]; then
            kill -9 $runningPID
        fi
        clear
        echo "$APP_NAME is stopped."
    else
        echo "$APP_NAME has not been started."
    fi
}

check(){
   if exist; then
   	 echo "$APP_NAME is alive."
   	 exit 0
   else
   	 echo "$APP_NAME is dead."
   	 exit -1
   fi
}

restart(){
    stop
    start
}

case "$1" in

start)
    start
;;
stop)
    stop
;;
restart)
    restart
;;
check)
    check
;;
*)
    echo "available operations: [start|stop|restart|check]"
    exit 1
;;
esac
