package com.ejyi.demo.executor;


import java.io.Serializable;
import java.util.concurrent.ThreadPoolExecutor;

public class Productioner implements Runnable, Serializable {

    private ThreadPoolExecutor executor;

    private Boolean runType;

    public Productioner(ThreadPoolExecutor executor){
        this.executor = executor;
        this.runType = true;
    }


    @Override
    public void run() {

        Integer task = 1;
        while (true){
            System.out.println("push_task:"+task+";queue_count:"+executor.getQueue().size()+";active_count:"+
                    executor.getActiveCount()+";pool_count:"+executor.getPoolSize());

            executor.execute(new Worker(task));

            task++;

            try {
                Thread.sleep(100L);
                if(task > 120){
                    Thread.sleep(400L);
                }
                if(!this.runType){
                    executor.shutdown();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("===================end========================");
    }



    public void stop(){
        this.runType = false;
    }


}
