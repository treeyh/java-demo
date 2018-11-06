package com.ejyi.demo.character.lambda.finterface;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-10-22 下午6:19
 */
@FunctionalInterface
public interface Interface1 {
    int doubleNum(int i);

    default int add(int i, int j){
        return i+j;
    }
}
