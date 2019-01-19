package com.ejyi.demo.springboot.server.dao.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author tree
 * @version 1.0
 * @description 描述
 * @create 2018-11-11 11:26 AM
 */
@Component
public class FlyWayRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==================================================");
    }
}
