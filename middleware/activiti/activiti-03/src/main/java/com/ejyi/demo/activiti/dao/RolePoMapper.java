package com.ejyi.demo.activiti.dao;

import com.ejyi.demo.activiti.dao.po.RolePo;

public interface RolePoMapper {
    int deleteByPrimaryKey(String id);

    int insert(RolePo record);

    int insertSelective(RolePo record);

    RolePo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePo record);

    int updateByPrimaryKey(RolePo record);
}