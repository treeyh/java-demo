package com.ejyi.demo.springboot.shardingjdbc.service;


import com.ejyi.demo.springboot.shardingjdbc.mapper.ConfigMapper;
import com.ejyi.demo.springboot.shardingjdbc.mapper.OrderMapper;
import com.ejyi.demo.springboot.shardingjdbc.mapper.UserMapper;
import com.ejyi.demo.springboot.shardingjdbc.po.Config;
import com.ejyi.demo.springboot.shardingjdbc.po.Order;
import com.ejyi.demo.springboot.shardingjdbc.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ConfigMapper configMapper;



    public void demoUser(){

        List<Integer> ids = new ArrayList<>();

        for(int i=0;i<10; i++){
            User user = new User();
            user.setUserId(i);
            user.setCreateTime(new Date());
            user.setLastUpdateTime(new Date());
            user.setName("aaaaa_"+i);
            user.setStatus(1);
            userMapper.insert(user);
        }
    }
    public void demoOrder(){

        List<Integer> ids = new ArrayList<>();
        for(Integer userId=1; userId<10; userId++){
            for(Integer orderId = userId * 10; orderId < (userId+1)*10; orderId++) {
                System.out.println("i:" + userId);
                Order order = new Order();
                order.setOrderId(orderId);
                order.setOrderNo("order_no_" + orderId);
                order.setAmount(Long.parseLong(userId.toString()));
                order.setCreateTime(new Date());
                order.setLastUpdateTime(new Date());
                order.setUserId(userId);
                order.setStatus(1);
                orderMapper.insert(order);
            }
        }
    }


    public void demoConfig(){

        List<Integer> ids = new ArrayList<>();

        for(int i=0;i<10; i++){
            Config config = new Config();
            config.setConfigId(i);
            config.setConfigKey("key_"+i);
            config.setConfigValue("value_"+i);
            config.setStatus(1);
            configMapper.insert(config);
        }
    }
}
