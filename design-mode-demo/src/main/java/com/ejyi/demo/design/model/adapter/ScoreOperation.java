package com.ejyi.demo.design.model.adapter;

// 抽象成绩操作类：目标接口
public interface ScoreOperation {
    int[] sort(int array[]); // 成绩排序
    int search(int array[],int key); // 成绩查找
}
