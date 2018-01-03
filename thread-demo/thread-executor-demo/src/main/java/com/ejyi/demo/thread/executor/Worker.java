package com.ejyi.demo.thread.executor;


import java.io.Serializable;

public class Worker implements Runnable, Serializable {


    private Integer taskData;

    public Worker(Integer taskData){
        System.out.println("create:"+Thread.currentThread().getName() + ";taskData:" + taskData);
        this.taskData = taskData;
    }


    @Override
    public void run() {

        //System.out.println("begin:"+Thread.currentThread().getName() + ";taskData:" + taskData);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end:"+Thread.currentThread().getName() + ";taskData:" + taskData);
    }


    public Integer getTaskData() {
        return this.taskData;
    }
}

