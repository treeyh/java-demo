package com.ejyi.demo.guava;

import com.google.common.base.Objects;

/**
 * http://www.yiibai.com/guava/guava_objects_class.html
 */
public class ObjectsDemo {


    public static void main(String[] args){

        Integer a=  null;
        Integer b= 1;
        System.out.println(Objects.equal(a, b));

        String s1 = "a";
        String s2 = "a";
        System.out.println(Objects.equal(s1, s2));
    }
}
