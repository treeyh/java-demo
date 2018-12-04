package com.ejyi.demo.activiti.dao;

import com.ejyi.demo.activiti.dao.po.RoleUserPo;

import java.util.List;

public interface RoleUserPoMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleUserPo record);

    int insertSelective(RoleUserPo record);

    RoleUserPo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleUserPo record);

    int updateByPrimaryKey(RoleUserPo record);


    List<RoleUserPo> selectByUserId(String userId);

    int deleteByUserId(String userId);

    int deleteByRoleId(String role);
}