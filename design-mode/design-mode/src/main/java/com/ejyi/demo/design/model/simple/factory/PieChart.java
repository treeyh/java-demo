package com.ejyi.demo.design.model.simple.factory;

public class PieChart implements Chart {

    public PieChart() {
        System.out.println("创建饼状图！");
    }

    public void display() {
        System.out.println("显示饼状图！");
    }

}
