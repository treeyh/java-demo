package com.ejyi.demo.design.model.adapter;

// 操作适配器：适配器
public class OperationAdapter implements ScoreOperation {
    private QuickSort sortObj; // 定义适配者 QuickSort 对象
    private BinarySearch searchObj; // 定义适配者 BinarySearch 对象

    public OperationAdapter() {
        sortObj = new QuickSort();
        searchObj = new BinarySearch();
    }

    public int[] sort(int array[]) {
        return sortObj.quickSort(array); // 调用适配者类 QuickSort 的排序方法
    }

    public int search(int array[],int key) {
        return searchObj.binarySearch(array,key); // 调用适配者类 BinarySearch 的查找方法
    }
}
