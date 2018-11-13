package com.ejyi.demo.activiti01.bo;

import java.io.Serializable;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-13 2:32 PM
 */
public class PersonBo implements Serializable {

    private Integer id;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
