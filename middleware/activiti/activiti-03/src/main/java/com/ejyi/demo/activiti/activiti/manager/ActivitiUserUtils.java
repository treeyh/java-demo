package com.ejyi.demo.activiti.activiti.manager;

import com.ejyi.demo.activiti.dao.po.RolePo;
import com.ejyi.demo.activiti.dao.po.UserPo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 余海
 * @version 1.0
 * @description 将业务中自己定义的用户、角色转化为activiti中使用的user、group
 * @create 2018-12-04 5:33 PM
 */
public class ActivitiUserUtils {

    public static UserEntity toActivitiUser(UserPo bUser) {
        UserEntity userEntity = new UserEntityImpl();
        userEntity.setId(bUser.getName());
        userEntity.setFirstName(bUser.getNameCn());
        userEntity.setLastName(bUser.getNameEn());
        userEntity.setEmail(bUser.getEmail());
        userEntity.setRevision(bUser.getVersion().intValue());
        return userEntity;
    }

    public static Group toActivitiGroup(RolePo rolePo) {
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
