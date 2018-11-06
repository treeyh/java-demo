package com.ejyi.demo.character.lambda.finterface;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-10-22 下午6:25
 */
@FunctionalInterface
interface Interface3 extends Interface1, Interface2 {

    @Override
    default int add(int x, int y) {
        // 指定使用哪一个接口的默认方法实现
        return Interface1.super.add(x, y);
    }
}
