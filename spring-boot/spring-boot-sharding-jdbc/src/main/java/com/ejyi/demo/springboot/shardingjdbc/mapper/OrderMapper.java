package com.ejyi.demo.springboot.shardingjdbc.mapper;

import com.ejyi.demo.springboot.shardingjdbc.po.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}