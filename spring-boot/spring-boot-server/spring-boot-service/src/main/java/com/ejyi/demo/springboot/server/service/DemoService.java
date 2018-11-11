/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 业务逻辑处理类
 * Version: 1.0
 * Date: 18-5-9 上午11:10
 * LastModified: 18-5-9 上午11:10
 */

package com.ejyi.demo.springboot.server.service;


import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.model.IpModel;
import com.ejyi.demo.springboot.server.model.result.CallResult;
import com.ejyi.demo.springboot.server.model.result.ReturnPageResult;

public interface DemoService {

    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    CallResult<DemoModel> queryById(Long id);

    /**
     * 创建对象
     * @param demoModel
     * @return
     */
    CallResult<DemoModel> createDemo(DemoModel demoModel);


    /**
     * 删除对象
     * @param id
     * @return
     */
    CallResult<Integer> deleteById(Long id);

    /**
     * 更新对象
     * @param id
     * @param demoModel
     * @return
     */
    CallResult<Integer> updateById(Long id, DemoModel demoModel);

    /**
     * 分页查询
     * @param code
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    CallResult<ReturnPageResult<DemoModel>> queryByPage(String code, Integer status, Long pageNum, Long pageSize);

    /**
     * 查询ip信息
     * @param ip
     * @return
     */
    CallResult<IpModel> queryByIp(String ip);
}
