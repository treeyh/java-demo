package com.ejyi.demo.springboot.server.web.listener;

import com.ejyi.demo.springboot.server.constants.AppConstants;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-11 4:59 PM
 */
@Component
public class GracefulStartListener implements TomcatConnectorCustomizer, ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(GracefulStartListener.class);

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info("Application start...");

        if(!WebApplicationType.SERVLET.equals(applicationReadyEvent.getSpringApplication().getWebApplicationType())){
            AppConstants.SERVICE_RUN_STATUS = 3;
            logger.info("Application start over.");
            return;
        }

        //设置5秒的启动缓冲接入时间
        try{
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        //设置应用状态为可用
        AppConstants.SERVICE_RUN_STATUS = 1;

        logger.info("Application start over.");

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(applicationReadyEvent.getApplicationContext().getBean(GracefulShutdownListener.class));

        logger.info("Application add shutdown listener over.");
    }
}