package com.ejyi.demo.activiti04.activiti.manager;

import com.ejyi.demo.activiti04.dao.RolePoMapper;
import com.ejyi.demo.activiti04.dao.RoleUserPoMapper;
import com.ejyi.demo.activiti04.dao.UserPoMapper;
import com.ejyi.demo.activiti04.dao.po.RolePo;
import com.ejyi.demo.activiti04.dao.po.RoleUserPo;
import com.ejyi.demo.activiti04.dao.po.UserPo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.CountingExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntityManagerImpl;
import org.activiti.engine.impl.persistence.entity.data.DataManager;
import org.activiti.engine.impl.persistence.entity.data.GroupDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 余海
 * @version 1.0
 * @description 自定义角色管理,具体方法进入GroupEntityManager中查看
 * @create 2018-12-04 5:59 PM
 */
public class CustomGroupEntityManager extends GroupEntityManagerImpl {

    @Autowired
    private UserPoMapper userPoMapper;
    @Autowired
    private RolePoMapper rolePoMapper;
    @Autowired
    private RoleUserPoMapper roleUserPoMapper;

    public CustomGroupEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration, GroupDataManager groupDataManager) {
        super(processEngineConfiguration, groupDataManager);
    }


    @Override
    public GroupEntity findById(String entityId) {
        RolePo rolePo = rolePoMapper.selectByPrimaryKey(entityId);
        return ActivitiUserUtils.toActivitiGroup(rolePo);
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
        UserPo userPo  =  userPoMapper.selectByPrimaryKey(userId);
        List<RoleUserPo> roleUserPos = roleUserPoMapper.selectByUserId(userPo.getId());
        List<Group> gs=new ArrayList<Group>();
        GroupEntity groupEntity;

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
