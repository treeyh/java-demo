package com.ejyi.demo.guava;

import com.ejyi.demo.model.dto.RestRespDto;
import com.google.common.base.MoreObjects;

public class MoreObjectsDemo {

    public static void main(String[] args){

        RestRespDto restRespDto = RestRespDto.success();
        System.out.println(restRespDto.getMessage());
        System.out.println(MoreObjects.toStringHelper(restRespDto).add("message","aaaaaaa").toString());


    }
}
