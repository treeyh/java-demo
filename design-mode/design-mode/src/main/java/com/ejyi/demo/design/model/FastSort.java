package com.ejyi.demo.design.model;

// 快速排序  http://www.xuetangx.com/courses/course-v1:TsinghuaX+30240184X+sp/courseware/c15aad6e2dac4250934ea61d71deafd9/7d5cdb2eee004579ac0a20be2678495b/

import java.util.ArrayList;
import java.util.List;

public class FastSort {


    private static int[] ids = new int[]{76, 9, 83, 22, 54, 10, 36, 77, 47, 29};

    public static int[] sort(int[] ids, int l , int r){
        if(l < r) {
            int i = l, j = r;
            int x = ids[i];
            while (i < j) {
                //从右向左查找，比x小的数，发现后交换ids[i]和ids[j]
                for (; i < j; j--) {
                    if (ids[j] < x) {
                        ids[i] = ids[j];
                        i++;
                        print(ids, i, j);
                        break;
                    }
                }

                System.out.println(";"+i+";"+j);

                //从左向右查找比x大的数，发现后交换ids[j]和ids[i]
                for (; i < j; i++) {

                    if (ids[i] > x) {
                        ids[j] = ids[i];
                        j--;
                        print(ids, i, j);
                        break;
                    }
                }
            }
            ids[i] = x;

            System.out.println(";"+i+"-"+j);

            //排序左边子区间
            ids = sort(ids, l, i - 1);
            //排序右边子区间
            ids = sort(ids, i+1, r);
        }

        return ids;
    }


    private static void print(int[] ids, int l, int r){
        for(int i : ids){
            System.out.print(i + ",");
        }
        System.out.print("---"+l + ","+ r);

        System.out.println();
    }



    public static int[] sort1(int[] ids, int l, int r){
        if(l >= r){
            return ids;
        }

        int i = l, j = r;
        int x = ids[i];
        while (i < j){

            for(;i < j; j--){
                if(ids[j] < x){
                    ids[i] = ids[j];
                    i++;
                    print(ids, i, j);
                    break;
                }
            }

            for(; i < j ; i++){
                if(ids[i] > x){
                    ids[j] = ids[i];
                    j --;
                    print(ids, i, j);
                    break;
                }
            }
        }
        ids[i] = x;

        System.out.println(";"+i+"-"+j);
        sort1(ids, l, i-1);
        sort1(ids, i+1, r);

        return ids;
    }


    public static void sort2(int[] ids, int l, int r){
        if(l >= r){
            return ;

        }

        int i = l , j = r;
        int x = ids[l];

        while (i < j){
            for(; i < j; j--){
                if(ids[j] < x){
                    ids[i] = ids[j];
                    i++;
                    break;
                }
            }

            for(; i<j ; i++){
                if(ids[i] > x){
                    ids[j] = ids[i];
                    j--;
                    break;
                }
            }
        }

        ids[i] = x;
        sort2(ids, l, i-1);
        sort2(ids, i+1, j);
    }





    public static void sort3(int[] ids, int l, int r){

        if(l >= r){
            return;
        }

        int i = l, j = r;
        int x = ids[i];

        while (i < j){
            for(; i < j; j--){
                if(ids[j] < x){
                    ids[i] = ids[j];
                    i++;
                    break;
                }
            }

            for(; i < j; i++){
                if(ids[i] > x){
                    ids[j] = ids[i];
                    j--;
                    break;
                }
            }
        }
        ids[i] = x;

        sort3(ids, l, i - 1 );
        sort3(ids, i + 1, r );
    }


    public static void sort31(int[] ids, int l, int r){
        if(l >= r){
            return;
        }


        int m = (l+r) >> 1;
        sort31(ids, l, m);
        sort31(ids,  m + 1, r);
        sort32(ids, l, m , r);
    }

    public static void sort32(int[] ids, int l, int mi , int r){

        int[] ls = new int[mi-l+1];

        for(int i = l; i <= mi; i++){
            ls[i-l] = ids[i];
        }
        int[] rs = new int[r - mi];
        for(int i= mi+1; i<= r;i++){
            rs[i - mi- 1] = ids[i];
        }

        for(int i = l, j = 0, k = 0; j < ls.length || k < rs.length ;){
            if(j < ls.length && (k >= rs.length || ls[j] <= rs[k])){
                ids[i++] = ls[j++];
            }
            if(k < rs.length && (j >= ls.length || ls[j] > rs[k])){
                ids[i++] = rs[k++];
            }
        }

    }



    public static void sort5(int[] ids, int l, int r){

        if(l >= r){
            return;
        }

        int i = l, j = r;
        int x = ids[l];

        while (i < j){
            for(; i < j; j --){
                if(ids[j] <= x){
                    ids[i++] = ids[j];
                    break;
                }
            }
            for(; i<j; i++){
                if(ids[i] >= x){
                    ids[j--] = ids[i];
                    break;
                }
            }
        }
        ids[i] = x;

        sort5(ids, l, i-1);
        sort5(ids, i+1, r);
    }

    public static void sort6(int[] ids, int l, int r){
        if(l >= r){
            return;
        }

        int m = (l+r) >> 1;
        sort6(ids, l, m);
        sort6(ids, m+1, r);
        sort61(ids, l, m , r);
    }

    public static void sort61(int[] ids, int l, int m , int r){
        int[] ls = new int[m - l + 1];
        int[] rs = new int[r - m];
        for(int i = l; i <= m; i++){
            ls[i - l] = ids[i];
        }
        for(int i = m+1; i<=r ;i++){
            rs[i - m - 1] = ids[i];
        }


        for(int i=l, j=0,k = 0; j < ls.length || k < rs.length ;){
            if(j<ls.length && (k>= rs.length || ls[j] <= rs[k])){
                ids[i++] = ls[j++];
            }
            if(k<rs.length && (j >= ls.length || ls[j] >= rs[k])){
                ids[i++] = rs[k++];
            }
        }
     }

    public static void main(String[] args) {

        sort6(ids, 0, ids.length -1);

        print(ids,0,0);
    }



}
