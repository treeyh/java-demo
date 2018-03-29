package com.ejyi.demo.design.model.factory.method;

public class FileLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        // 创建文件日志记录器对象
        Logger logger = new FileLogger();
        // 创建文件，代码省略
        return logger;
    }
}