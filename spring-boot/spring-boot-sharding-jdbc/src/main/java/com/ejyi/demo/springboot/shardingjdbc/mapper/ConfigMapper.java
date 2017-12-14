package com.ejyi.demo.springboot.shardingjdbc.mapper;

import com.ejyi.demo.springboot.shardingjdbc.po.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}