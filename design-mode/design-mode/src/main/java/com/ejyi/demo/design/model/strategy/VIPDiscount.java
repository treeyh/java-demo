package com.ejyi.demo.design.model.strategy;


//VIP 会员票折扣类：具体策略类
public class VIPDiscount implements Discount {
    public double calculate(double price) {
        System.out.println("VIP 票：");
        System.out.println("增加积分！");
        return price * 0.5;
    }

}
