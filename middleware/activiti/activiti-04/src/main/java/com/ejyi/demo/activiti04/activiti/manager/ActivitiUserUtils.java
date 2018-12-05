package com.ejyi.demo.activiti04.activiti.manager;

import com.ejyi.demo.activiti04.dao.po.RolePo;
import com.ejyi.demo.activiti04.dao.po.UserPo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 余海
 * @version 1.0
 * @description 将业务中自己定义的用户、角色转化为activiti中使用的user、group
 * @create 2018-12-04 5:33 PM
 */
public class ActivitiUserUtils {

    public static UserEntity toActivitiUser(UserPo userPo) {
        if(null == userPo){
            return null;
        }

        UserEntity userEntity = new UserEntityImpl();
        userEntity.setId(userPo.getName());
        userEntity.setFirstName(userPo.getNameCn());
        userEntity.setLastName(userPo.getNameEn());
        userEntity.setEmail(userPo.getEmail());
        userEntity.setRevision(userPo.getVersion().intValue());
        return userEntity;
    }

//    public static UserPo fromActivitiUser(UserEntity userEntity){
//        UserPo userPo = new UserPo();
//        userPo.setId(userEntity.getId());
//        userPo.setCreateTime(new Date());
//        userPo.setEmail(userEntity.getEmail());
//        userPo.setCreateTime();
//
//    }

    public static GroupEntity toActivitiGroup(RolePo rolePo) {
        if(null == rolePo){
            return null;
        }
        GroupEntity groupEntity = new GroupEntityImpl();
        groupEntity.setRevision(1);
        groupEntity.setType(rolePo.getType()); //"assignment"
        groupEntity.setId(rolePo.getId());
        groupEntity.setName(rolePo.getName());
        return groupEntity;
    }

    public static List<Group> toActivitiGroups(List<RolePo> rolePos) {
        List<Group> groups = new ArrayList<Group>();
        for (RolePo role : rolePos) {
            Group groupEntity = toActivitiGroup(role);
            groups.add(groupEntity);
        }
        return groups;
    }

}
