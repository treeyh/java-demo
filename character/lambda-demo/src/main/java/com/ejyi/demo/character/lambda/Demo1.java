package com.ejyi.demo.character.lambda;

import com.ejyi.demo.character.lambda.finterface.Interface1;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-10-22 下午6:19
 */
public class Demo1 {

    public static void main(String[] args) {
        Interface1 interface1 = (i) -> i*2;
        Interface1 interface2 = (i) -> {
            System.out.println(i);
            return i*2;
        };

        System.out.println(interface1.doubleNum(2));
        System.out.println(interface2.doubleNum(2));
    }
}
