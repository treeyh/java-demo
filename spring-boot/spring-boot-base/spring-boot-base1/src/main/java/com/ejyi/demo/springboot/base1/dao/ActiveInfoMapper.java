package com.ejyi.demo.springboot.base1.dao;

import com.ejyi.demo.springboot.base1.po.ActiveInfo;
import com.ejyi.demo.springboot.base1.po.ActiveInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActiveInfoMapper {
    int countByExample(ActiveInfoExample example);

    int deleteByExample(ActiveInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActiveInfo record);

    int insertSelective(ActiveInfo record);

    List<ActiveInfo> selectByExample(ActiveInfoExample example);

    ActiveInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActiveInfo record, @Param("example") ActiveInfoExample example);

    int updateByExample(@Param("record") ActiveInfo record, @Param("example") ActiveInfoExample example);

    int updateByPrimaryKeySelective(ActiveInfo record);

    int updateByPrimaryKey(ActiveInfo record);
}