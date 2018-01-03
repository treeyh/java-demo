package com.ejyi.demo.thread.executor;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ExecutorDemo {

    private LinkedBlockingDeque<Runnable> linkedBlockingDeque = new LinkedBlockingDeque<Runnable>(30);

    private ThreadPoolExecutor executor;

    private Productioner productioner;

    private void start(){
        //ExecutorService exec = Executors.newCachedThreadPool();

        executor  = new ThreadPoolExecutor(2, 4, 2000L,
                TimeUnit.MILLISECONDS, linkedBlockingDeque);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        productioner = new Productioner(executor);
        Thread thread = new Thread(productioner);
        thread.start();
    }


    public void startThread(){
        this.start();
    }

    public void stopThread(){
        productioner.stop();
    }

    public static void main(String[] args) {

        ExecutorDemo executorDemo = new ExecutorDemo();
        executorDemo.startThread();

        try {
            Thread.sleep(16000L);
            executorDemo.stopThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------end----------------------");
    }
}

