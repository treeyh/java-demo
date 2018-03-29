package com.ejyi.demo.springboot.base1.service;

import com.ejyi.demo.springboot.base1.po.ActiveInfo;
import org.springframework.transaction.annotation.Transactional;


public interface ActiveInfoService {
    ActiveInfo addActiveInfo(String code, Integer size, Double score, Integer type, Integer status);

    @Transactional(rollbackFor = RuntimeException.class)
    void addActiveInfo(ActiveInfo activeInfo);

    @Transactional
    void addActiveInfo2(ActiveInfo activeInfo);
}
