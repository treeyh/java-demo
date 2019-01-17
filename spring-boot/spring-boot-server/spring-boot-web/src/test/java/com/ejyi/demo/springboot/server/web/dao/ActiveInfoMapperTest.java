package com.ejyi.demo.springboot.server.web.dao;

import com.ejyi.demo.springboot.server.dao.ActiveInfoMapper;
import com.ejyi.demo.springboot.server.dao.config.DatasourceConfig;
import com.ejyi.demo.springboot.server.dao.po.ActiveInfoPO;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2019-01-07 4:00 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DatasourceConfig.class, MybatisAutoConfiguration.class})
public class ActiveInfoMapperTest {


    @Autowired
    private ActiveInfoMapper activeInfoMapper;


    @Test
    public void insert() throws Exception {

        int count = 0;
        ActiveInfoPO activeInfoPO = new ActiveInfoPO(10L, "code", 10, 1.1D, 1, 1, new Date(), new Date());


        count = activeInfoMapper.insert(activeInfoPO);
        assertEquals(1, count);
        System.out.printf("%s", activeInfoPO);

    }


    @Test
    public void queryById() throws Exception {


        ActiveInfoPO activeInfoPO = activeInfoMapper.selectById(2L);

        Assertions.assertThat(activeInfoPO.getId()).isEqualTo(2L);
        System.out.printf("%s", JsonUtils.toJson(activeInfoPO));

    }

}
