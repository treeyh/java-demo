package com.ejyi.demo.springboot.base.demo.job;

import com.ejyi.demo.common.helper.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobDemo {

    private static final Logger logger = LoggerFactory.getLogger(JobDemo.class);

    /**
     * delay
     */
    //@Scheduled(fixedDelay = 5*1000)   //上次执行完5秒后执行一次
    public void delay(){

        logger.info("delay_run:"+ DateHelper.getDateTimeStringBy_yyyyMMddHHmmss());

        this.sleep(2000L);
    }

    /**
     * delay
     */
    //@Scheduled(fixedRate = 5*1000)   //每隔5秒执行一次
    public void rate(){

        logger.info("rate_run:"+ DateHelper.getDateTimeStringBy_yyyyMMddHHmmss());

        this.sleep(2000L);
    }


    /**
     * delay
     */
    //@Scheduled(cron = "*/5 * * * * ?")   //每隔5秒执行一次
    public void cron(){

        logger.info("cron_run:"+ DateHelper.getDateTimeStringBy_yyyyMMddHHmmss());

        this.sleep(2000L);
    }





    private void sleep(Long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


