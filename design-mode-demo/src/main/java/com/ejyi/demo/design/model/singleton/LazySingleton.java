package com.ejyi.demo.design.model.singleton;

public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        // 第一重判断
        if (instance == null) {
            // 锁定代码块
            synchronized (LazySingleton.class) {
                // 第二重判断
                if (instance == null) {
                    instance = new LazySingleton(); // 创建单例实例
                }
            }
        }
        return instance;
    }
}
