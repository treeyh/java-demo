package com.ejyi.demo.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapDemo {
    public static void main(String args[]){
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");

        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Mahesh"));


        BiMap<String, String> empIDNameMap2 = HashBiMap.create();

        empIDNameMap2.put("1", "Mahesh");
        empIDNameMap2.put("2", "Sohan");
        empIDNameMap2.put("3", "Ramesh");

        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap2.inverse().get("Mahesh"));
    }
}
