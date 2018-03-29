package com.ejyi.demo.design.model;

import java.util.ArrayList;
import java.util.List;

//归并排序 http://www.xuetangx.com/courses/course-v1:TsinghuaX+30240184X+sp/courseware/706d387daf2d4495aeb7fea3246a82ce/2c649da951724376bc495067fafd21cb/
public class MergeSort {

    private static int[] ids = new int[]{76, 9, 83, 22, 54, 10, 36, 77, 47, 29};

    public static void sort(int[] ids, int l , int r){

        if(r-l < 1){
            return ;
        }

        int mi = (l + r) >> 1; //取中间索引值
        sort(ids, l, mi);
        sort(ids, mi+1, r);
        meger(ids, l, mi, r);

        print(ids, l, r);
    }

    public static void meger(int[] ids, int l, int mi, int r){

        List<Integer> tmpls =new ArrayList<Integer>(mi - l + 1);
        //复制出左边一段
        for(int i = l; i <= mi; i++){
            tmpls.add(ids[i]);
        }
//        System.out.println(tmpls);
        List<Integer> tmprs =new ArrayList<Integer>(r - mi );
        for(int i = mi+1; i <= r; i++){
            tmprs.add(ids[i]);
        }
//        System.out.println(tmprs);

        for(int i = l, j = 0, k = 0; (j < tmpls.size()) || (k < tmprs.size()); ){
//            System.out.println(i+"-"+ j+"-"+ k+"-"+tmpls.size()+"-"+tmprs.size());
            if(j < tmpls.size() &&
                    ((k >= tmprs.size())
                            || (tmpls.get(j) <= tmprs.get(k)))){
                ids[i++] = tmpls.get(j++);
            }
//            System.out.println(i+"-"+ j+"-"+ k+"-"+tmpls.size()+"-"+tmprs.size());
            if(k< tmprs.size() && ((j >= tmpls.size() ) || (tmpls.get(j) > tmprs.get(k)))){
                ids[i++] = tmprs.get(k++);
            }
        }
    }

    private static void print(int[] ids, int l, int r){
        for(int i : ids){
            System.out.print(i + ",");
        }
        System.out.print("---"+l + ","+ r);

        System.out.println();
    }



    public static void main(String[] args) {
        sort(ids, 0, ids.length-1);
    }

}
