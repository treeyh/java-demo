/*
 *
 *  * ProjectName: springboot-parent
 *  * Author: tree.yu
 *  * Description: 仓库上下文对象
 *  * Version: 1.0
 *  * Date: 18-5-9 上午11:02
 *  * LastModified: 18-5-9 上午11:02
 *
 */

package com.ejyi.demo.springboot.server.manager.domain.repository;


import com.ejyi.demo.springboot.server.manager.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("repositoryContext")
public class RepositoryContext {

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private DemoModelRepository demoModelRepository;

//    @Autowired
//    private IpClient ipClient;


    public RedisHelper getRedisHelper() {
        return redisHelper;
    }
    public DemoModelRepository getDemoModelRepository() {
        return demoModelRepository;
    }


}
