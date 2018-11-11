/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-17 下午9:07
 * LastModified: 18-5-11 上午10:09
 */

package com.ejyi.demo.springboot.server.dao;

import java.util.List;

import com.ejyi.demo.springboot.server.dao.po.ActiveInfoPO;
import com.ejyi.demo.springboot.server.dao.po.ActiveInfoExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ActiveInfoMapper {
    int countByExample(ActiveInfoExample example);

    int deleteByExample(ActiveInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActiveInfoPO record);

    int insertSelective(ActiveInfoPO record);

    List<ActiveInfoPO> selectByExample(ActiveInfoExample example);

    ActiveInfoPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActiveInfoPO record, @Param("example") ActiveInfoExample example);

    int updateByExample(@Param("record") ActiveInfoPO record, @Param("example") ActiveInfoExample example);

    int updateByPrimaryKeySelective(ActiveInfoPO record);

    int updateByPrimaryKey(ActiveInfoPO record);

    @Select("select id, code, size, score, type, status, create_time, update_time from active_info where id = #{id}")
    ActiveInfoPO selectById(@Param("id") Long id);

    @Delete("delete from active_info where id = #{id}")
    int deleteById(@Param("id") Long id);



}