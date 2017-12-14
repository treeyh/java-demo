package com.ejyi.demo.springboot.shardingjdbc.mapper;

import com.ejyi.demo.springboot.shardingjdbc.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}