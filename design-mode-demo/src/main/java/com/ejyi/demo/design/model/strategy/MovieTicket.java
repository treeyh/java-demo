package com.ejyi.demo.design.model.strategy;

//策略模式

// 电影票类：环境类
public class MovieTicket {
    private double price;
    private Discount discount; // 维持一个对抽象折扣类的引用

    public void setPrice(double price) {
        this.price = price;
    }

    // 注入一个折扣类对象
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getPrice() {
        // 调用折扣类的折扣价计算方法
        return discount.calculate(this.price);
    }
}
