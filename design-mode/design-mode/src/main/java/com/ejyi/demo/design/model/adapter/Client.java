package com.ejyi.demo.design.model.adapter;

public class Client {

    //http://blog.csdn.net/lovelion/article/details/8624412

    public static void main(String args[]) {
        ScoreOperation operation;  // 针对抽象目标接口编程
        operation = (ScoreOperation)XMLUtil.getBean(); // 读取配置文件，反射生成对象
        int scores[] = {84,76,50,69,90,91,88,96}; // 定义成绩数组
        int result[];
        int score;

        System.out.println("成绩排序结果：");
        result = operation.sort(scores);

        // 遍历输出成绩
        for(int i : scores) {
            System.out.print(i + ",");
        }
        System.out.println();

        System.out.println("查找成绩 90：");
        score = operation.search(result,90);
        if (score != -1) {
            System.out.println("找到成绩 90。");
        }
        else {
            System.out.println("没有找到成绩 90。");
        }

        System.out.println("查找成绩 92：");
        score = operation.search(result,92);
        if (score != -1) {
            System.out.println("找到成绩 92。");
        }
        else {
            System.out.println("没有找到成绩 92。");
        }
    }
}


/*
 1. 主要优点

       无论是对象适配器模式还是类适配器模式都具有如下优点：

       (1) 将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，无须修改原有结构。

       (2) 增加了类的透明性和复用性，将具体的业务实现过程封装在适配者类中，对于客户端类而言是透明的，而且提高了适配者的复用性，同一个适配者类可以在多个不同的系统中复用。

       (3) 灵活性和扩展性都非常好，通过使用配置文件，可以很方便地更换适配器，也可以在不修改原有代码的基础上增加新的适配器类，完全符合 “开闭原则”。

      具体来说，类适配器模式还有如下优点：

      由于适配器类是适配者类的子类，因此可以在适配器类中置换一些适配者的方法，使得适配器的灵活性更强。

      对象适配器模式还有如下优点：

      (1) 一个对象适配器可以把多个不同的适配者适配到同一个目标；

      (2) 可以适配一个适配者的子类，由于适配器和适配者之间是关联关系，根据 “里氏代换原则”，适配者的子类也可通过该适配器进行适配。

 */