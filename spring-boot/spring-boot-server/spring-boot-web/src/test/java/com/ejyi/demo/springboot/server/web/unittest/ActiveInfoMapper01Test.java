package com.ejyi.demo.springboot.server.web.unittest;

import com.ejyi.demo.springboot.server.dao.ActiveInfoMapper;
import com.ejyi.demo.springboot.server.dao.po.ActiveInfoPO;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author tree
 * @version 1.0
 * @description
 * @create 2019-01-17 8:25 PM
 */
public class ActiveInfoMapper01Test extends BaseTest {

    @Autowired
    private ActiveInfoMapper activeInfoMapper;

    @Test
    public void insert() throws Exception {
        int count = 0;
        Long time = System.currentTimeMillis();
        ActiveInfoPO activeInfoPO = new ActiveInfoPO(time, time.toString(), 10, 1.1D, 1, 1, new Date(), new Date());
        count = activeInfoMapper.insert(activeInfoPO);
        assertEquals(1, count);
        System.out.printf("==========================================ActiveInfoMapper01Test.insert%s", JsonUtils.toJson(activeInfoPO));
    }


    @Test
    public void queryById() throws Exception {
        ActiveInfoPO activeInfoPO = activeInfoMapper.selectById(2L);
        Assertions.assertThat(activeInfoPO.getId()).isEqualTo(2L);
        System.out.printf("==========================================ActiveInfoMapper01Test.queryById%s", JsonUtils.toJson(activeInfoPO));
    }
}
