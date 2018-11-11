/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-17 下午9:11
 * LastModified: 18-5-17 上午9:39
 */

/*
 *
 *  * ProjectName: springboot-parent
 *  * Author: tree.yu
 *  * Description: ActiveInfo 领域对象
 *  * Version: 1.0
 *  * Date: 18-5-9 上午10:49
 *  * LastModified: 18-5-9 上午10:49
 *
 */

package com.ejyi.demo.springboot.server.manager.domain;


import com.alibaba.fastjson.JSON;
import com.ejyi.demo.springboot.server.constants.AppConstants;
import com.ejyi.demo.springboot.server.exception.SysErrorException;
import com.ejyi.demo.springboot.server.manager.domain.repository.RepositoryContext;
import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.model.enums.ResultEnum;
import com.ejyi.demo.springboot.server.model.result.ReturnPageResult;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class DemoModelDomain {


    private RepositoryContext repositoryContext;

    public DemoModelDomain(RepositoryContext repositoryContext){
        this.repositoryContext = repositoryContext;
    }


    public DemoModel queryDemoById(long id){

        String cacheKey = AppConstants.CACHE_KEY_PRE +"demomodel:"+ String.valueOf(id);
        String json = repositoryContext.getRedisHelper().get(cacheKey);
        if(StringUtils.isEmpty(json)){
            DemoModel demoModel = repositoryContext.getDemoModelRepository().queryById(id);

            if(null != demoModel){
                json = JSON.toJSONString(demoModel);
                repositoryContext.getRedisHelper().set(cacheKey, json, AppConstants.CACHE_TIME_OUT);
            }
        }

        return JsonUtils.fromJson(json, DemoModel.class);
    }

    public DemoModel queryDemoByCode(String code){

        List<DemoModel> demoModels = repositoryContext.getDemoModelRepository().queryByCode(code);
        if(null == demoModels || 0 == demoModels.size()){
            return null;
        }
        return demoModels.get(0);
    }

    /**
     * 创建对象
     * @param demoModel
     * @return
     */
    public int insertDemo(DemoModel demoModel) throws SysErrorException {
        if(demoModel == null){
            return 0;
        }
        if(null != this.queryDemoByCode(demoModel.getCode())){
            throw new SysErrorException(ResultEnum.ACTIVEINFO_EXIST);
        }
        return repositoryContext.getDemoModelRepository().insert(demoModel);
    }


    /**
     * 根据id删除对象
     * @param id
     * @return
     * @throws SysErrorException
     */
    public int deleteById(long id) throws SysErrorException {

        if(null == this.queryDemoById(id)){
            throw new SysErrorException(ResultEnum.ACTIVEINFO_NOT_EXIST);
        }

        return repositoryContext.getDemoModelRepository().deleteById(id);
    }


    /**
     * 分页查询对象
     * @param code
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ReturnPageResult<DemoModel> queryByPage(String code, Integer status, Long pageNum, Long pageSize){
        return repositoryContext.getDemoModelRepository().queryByPage(code, status, pageNum, pageSize);
    }

    /**
     * 更新对象
     * @param demoModel
     * @return
     */
    public int updateById(DemoModel demoModel) throws SysErrorException {

        DemoModel demoModel1 = this.queryDemoById(demoModel.getId());

        if(null == demoModel1){
            throw new SysErrorException(ResultEnum.ACTIVEINFO_NOT_EXIST);
        }

        return repositoryContext.getDemoModelRepository().updateById(demoModel);
    }
}
