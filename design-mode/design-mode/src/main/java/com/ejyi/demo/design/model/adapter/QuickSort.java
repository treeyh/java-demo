package com.ejyi.demo.design.model.adapter;

// 快速排序类：适配者
public class QuickSort {
    public int[] quickSort(int array[]) {
        sort(array,0,array.length-1);
        return array;
    }

    public void sort(int array[],int p, int r) {
        int q=0;
        if(p<r) {
            q=partition(array,p,r);
            sort(array,p,q-1);
            sort(array,q+1,r);
        }
    }

    public int partition(int[] a, int p, int r) {
        int x=a[r];
        int j=p-1;
        for (int i=p;i<=r-1;i++) {
            if (a[i]<=x) {
                j++;
                swap(a,j,i);
            }
        }
        swap(a,j+1,r);
        return j+1;
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
