package com.ejyi.demo.design.model.factory.method;

class DatabaseLogger implements Logger {
    public void writeLog() {
        System.out.println("数据库日志记录。");
    }
}
