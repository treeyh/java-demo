package com.ejyi.demo.activiti.activiti.manager;

import com.ejyi.demo.activiti.dao.RolePoMapper;
import com.ejyi.demo.activiti.dao.RoleUserPoMapper;
import com.ejyi.demo.activiti.dao.UserPoMapper;
import com.ejyi.demo.activiti.dao.po.RolePo;
import com.ejyi.demo.activiti.dao.po.RoleUserPo;
import com.ejyi.demo.activiti.dao.po.UserPo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.GroupEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 余海
 * @version 1.0
 * @description 自定义角色管理,具体方法进入GroupEntityManager中查看
 * @create 2018-12-04 5:59 PM
 */
@Component
public class CustomGroupEntityManager extends GroupEntityManagerImpl {

    @Autowired
    private UserPoMapper userPoMapper;
    @Autowired
    private RolePoMapper rolePoMapper;
    @Autowired
    private RoleUserPoMapper roleUserPoMapper;

    @Override
    public Group createNewGroup(String groupId) {
        return super.createNewGroup(groupId);
    }

    /**
     * 查找角色
     * @param userId
     * @return
     */
    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if(userId==null){
            return null;
        }
        System.out.println("userId:"+userId);
        UserPo userPo  =  userPoMapper.selectByPrimaryKey(userId);
        List<RoleUserPo> roleUserPos = roleUserPoMapper.selectByUserId(userPo.getId());
        System.out.println("userRoleList size:"+roleUserPos.size());
        List<Group> gs=new ArrayList<Group>();
        GroupEntity groupEntity;
        String roleId;
        String activitiRole;
        for (RoleUserPo roleUserPo : roleUserPos) {
            groupEntity = new GroupEntityImpl();
            RolePo role = rolePoMapper.selectByPrimaryKey(roleUserPo.getRoleId());
            groupEntity.setRevision(1);
            groupEntity.setType(role.getType());  //"assignment"
            groupEntity.setId(role.getId());
            groupEntity.setName(role.getName());
            gs.add(groupEntity);
        }
        return gs;
    }

}