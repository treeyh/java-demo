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
 *  * Description: ActiveInfo 仓储类，用于持久化
 *  * Version: 1.0
 *  * Date: 18-5-9 上午10:53
 *  * LastModified: 18-5-9 上午10:53
 *
 */

package com.ejyi.demo.springboot.server.manager.domain.repository;

import com.ejyi.demo.springboot.server.dao.ActiveInfoMapper;
import com.ejyi.demo.springboot.server.dao.po.ActiveInfoPO;
import com.ejyi.demo.springboot.server.dao.po.ActiveInfoExample;
import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.model.result.ReturnPageResult;
import com.ejyi.demo.springboot.server.utils.ClazzConverterUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository("demoModelRepository")
public class DemoModelRepository {

    private static final Logger logger = LoggerFactory.getLogger(DemoModelRepository.class);

    @Autowired
    private ActiveInfoMapper activeInfoMapper;


    /**
     * 根据id查询
     * @param id
     * @return
     */
    public DemoModel queryById(long id){
        ActiveInfoPO activeInfo = activeInfoMapper.selectById(id);
        return ClazzConverterUtils.converterClass(activeInfo, DemoModel.class);
    }

    /**
     * 根据code查询
     * @param code
     * @return
     */
    public List<DemoModel> queryByCode(String code){

        ActiveInfoExample activeInfoExample = new ActiveInfoExample();
        activeInfoExample.createCriteria().andCodeEqualTo(code);

        List<ActiveInfoPO> activeInfos = activeInfoMapper.selectByExample(activeInfoExample);

        return (List<DemoModel>)ClazzConverterUtils.converterClass(activeInfos, DemoModel.class);
    }

    /**
     * 保存一条记录
     */
    public int insert(DemoModel demoModel){
        ActiveInfoPO activeInfo = ClazzConverterUtils.converterClass(demoModel, ActiveInfoPO.class);
        if(activeInfo == null){
            return 0;
        }

        activeInfo.setSize(1);
        activeInfo.setType(2);

        int rt = activeInfoMapper.insert(activeInfo);
        if(rt > 0){
            demoModel.setId(activeInfo.getId());
        }

        return rt;
    }


    /**
     * 删除对象
     * @param id
     * @return
     */
    public int deleteById(long id){
        return activeInfoMapper.deleteById(id);
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

        ActiveInfoExample example = new ActiveInfoExample();

        ActiveInfoExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(code)){
            criteria.andCodeLike(code + "%");
        }
        if(null != status && 0 < status){
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause("id desc"); // 设置排序方式

        int offset = (pageNum.intValue() - 1) * pageSize.intValue();

        example.setOffset(offset);
        example.setLimit(pageSize.intValue());

        Integer total = activeInfoMapper.countByExample(example);
        List<ActiveInfoPO> activeInfos = activeInfoMapper.selectByExample(example);

        if(null == activeInfos || activeInfos.size() <= 0){
            return new ReturnPageResult<DemoModel>(Long.parseLong(total.toString()), pageNum, pageSize, new ArrayList<DemoModel>());
        }

        List<DemoModel> demoModels = (List<DemoModel>)ClazzConverterUtils.converterClass(activeInfos, DemoModel.class);

        return new ReturnPageResult<DemoModel>(Long.parseLong(total.toString()), pageNum, pageSize, demoModels);

    }


    /**
     * 更新对象
     * @param demoModel
     * @return
     */
    public int updateById(DemoModel demoModel){

        ActiveInfoPO activeInfo = new ActiveInfoPO();
        activeInfo.setId(demoModel.getId());
        if(null != demoModel.getScore()) {
            activeInfo.setScore(demoModel.getScore());
        }
        if(null != demoModel.getStatus()) {
            activeInfo.setStatus(demoModel.getStatus().intValue());
        }
        activeInfo.setUpdateTime(new Date());

        ActiveInfoExample example = new ActiveInfoExample();
        example.createCriteria().andIdEqualTo(demoModel.getId());

        return activeInfoMapper.updateByExampleSelective(activeInfo, example);
    }


}
