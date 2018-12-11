package com.ejyi.demo.springboot.server.web.listener;

import com.ejyi.demo.springboot.server.constants.AppConstants;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 余海
 * @version 1.0
 * @description 优雅关闭 Spring Boot。容器必须是 tomcat
 * @create 2018-12-03 3:58 PM
 */
@Component
public class GracefulShutdownListener  implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(GracefulShutdownListener.class);

    private volatile Connector connector;
    private final int waitTime = 5;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        logger.info("Application end...");

        if(AppConstants.SERVICE_RUN_STATUS == 3){
            logger.info("Application end over");
            return;
        }

        //设置应用状态为准备停止
        AppConstants.SERVICE_RUN_STATUS = 2;
        try {
            //sleep一段时间供负载均衡心跳捕获
            Thread.sleep(25000L);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }

        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(waitTime, TimeUnit.SECONDS)) {
                    logger.warn("Tomcat 进程在" + waitTime + " 秒内无法结束，尝试强制结束");
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        logger.info("Application end over");
    }
}
