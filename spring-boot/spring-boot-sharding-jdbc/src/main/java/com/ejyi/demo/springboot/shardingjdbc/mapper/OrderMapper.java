package com.ejyi.demo.springboot.shardingjdbc.mapper;

import com.ejyi.demo.springboot.shardingjdbc.po.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}