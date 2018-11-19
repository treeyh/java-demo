package com.ejyi.demo.activiti02.activiti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-19 4:28 PM
 */
public class AuthService implements Serializable {

    public List<String> getCandidateUsers(){
        List<String> result = new ArrayList<>();
        result.add("userA");
        result.add("userB");
        return result;
    }
}
