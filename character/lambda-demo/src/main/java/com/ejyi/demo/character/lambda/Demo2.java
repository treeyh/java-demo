package com.ejyi.demo.character.lambda;

import com.ejyi.demo.character.lambda.model.Dog;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-10-22 下午6:36
 */
public class Demo2 {

    public static void main(String[] args) {
        // 方法引用，调用打印方法
        Consumer<String> consumer = System.out::println;
        consumer.accept("接收的数据");

        // 静态方法引用，通过类名即可调用
        Consumer<Dog> consumer2 = Dog::bark;
        consumer2.accept(new Dog());

        // 实例方法引用，通过对象实例进行引用
        Dog dog = new Dog();
        IntUnaryOperator function = dog::eat;
        System.out.println("还剩下" + function.applyAsInt(2) + "斤");

        // 另一种通过实例方法引用的方式，之所以可以这么干是因为JDK默认会把当前实例传入到非静态方法，参数名为this，参数位置为第一个，所以我们在非静态方法中才能访问this，那么就可以通过BiFunction传入实例对象进行实例方法的引用
        Dog dog2 = new Dog();
        BiFunction<Dog, Integer, Integer> biFunction = Dog::eat;
        System.out.println("还剩下" + biFunction.apply(dog2, 2) + "斤");

        // 无参构造函数的方法引用，类似于静态方法引用，只需要分析好输入输出即可
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象：" + supplier.get());

        // 有参构造函数的方法引用
        Function<String, Dog> function2 = Dog::new;
        System.out.println("创建了新对象：" + function2.apply("旺财"));
    }
}
