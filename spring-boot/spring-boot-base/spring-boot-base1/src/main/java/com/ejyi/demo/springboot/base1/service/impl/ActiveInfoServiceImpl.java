package com.ejyi.demo.springboot.base1.service.impl;

import com.ejyi.demo.springboot.base1.dao.ActiveInfoMapper;
import com.ejyi.demo.springboot.base1.po.ActiveInfo;
import com.ejyi.demo.springboot.base1.service.ActiveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ActiveInfoServiceImpl implements ActiveInfoService {

    @Autowired
    ActiveInfoMapper activeInfoMapper;

    @Override
    public ActiveInfo addActiveInfo(String code, Integer size, Double score, Integer type, Integer status){

        ActiveInfo activeInfo = new ActiveInfo();
        activeInfo.setCode(code);
        activeInfo.setSize(size);
        activeInfo.setScore(score);
        activeInfo.setType( type);
        activeInfo.setStatus(status);
        activeInfo.setCreateTime(new Date());
        activeInfo.setUpdateTime(new Date());

        addActiveInfo(activeInfo);
        return activeInfo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addActiveInfo(ActiveInfo activeInfo){

        activeInfoMapper.insert(activeInfo);

        throw new RuntimeException("error");

    }


    @Override
    @Transactional
    public void addActiveInfo2(ActiveInfo activeInfo){

        activeInfoMapper.insertSelective(activeInfo);


    }
}
