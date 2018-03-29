package com.ejyi.demo.thread.executor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

    public static void main(String[] args) {

//        System.out.println("aaa");
//
//        ArrayList<String> ss = new ArrayList<String>();
//
//        for(Integer i=0;i<9;i++){
//
//            ss.add(i.toString());
//        }
//        System.out.println(ss.size());
//        ss.add("111");
//        ss.add("111");
//        ss.get(1);
//
//        ss.remove("111");
//        ss.clear();
//
//        System.out.println(ss);


//        LinkedList<String> lls = new LinkedList<String>();
//
//        for(Integer i=0;i<9;i++){
//            lls.add(i.toString());
//        }
//
//        lls.addAll(lls);
//        lls.addFirst("a");
//        lls.get(2);
//        lls.remove(1);
//        lls.clear();
//        System.out.println(lls.size());
//
//        System.out.println(lls);


//        CopyOnWriteArrayList<String> cowls = new CopyOnWriteArrayList<String>();
//
//        for(Integer i=0;i<9;i++){
//            cowls.add(i.toString());
//        }
//
//        cowls.addAll(cowls);
//        cowls.get(2);
//        cowls.set(1,"a");
//        cowls.remove(1);
//        cowls.clear();
//        System.out.println(cowls.size());
//
//        System.out.println(cowls);


//        Vector<String> vs = new Vector<String>();
//        for(Integer i=0;i<9;i++){
//            vs.add(i.toString());
//        }
//
//        vs.addAll(vs);
//        vs.get(2);
//        vs.set(1,"a");
//        vs.remove(1);
//        vs.clear();
//        System.out.println(vs.size());
//
//        System.out.println(vs);

//        HashMap<String, String> map = new HashMap<>();
//        for(Integer i=0;i<12;i++){
//            if(i == 0){
//                map.put(i.toString(), i.toString());
//                continue;
//            }
//            map.put(i.toString(), i.toString());
//        }
//        map.put("acca","aadb");
//        map.get(2);
//        map.remove(1);
//        map.clear();
//        System.out.println(map.size());
//
//        System.out.println(map);


        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for(Integer i=0;i<12;i++){
            if(i == 0){
                map.put(i.toString(), i.toString());
                continue;
            }
            map.put(i.toString(), i.toString());
        }
        map.put("acca","aadb");
        map.get(2);
        map.remove(1);
        map.clear();
        System.out.println(map.size());

        System.out.println(map);



//        System.out.println("<-128~127 以内的 Integer 值，Integer x = value; 的方式赋值！>");
//        Integer i = 127;
//        Integer j = 127;
//        System.out.println("i=" + i + ",j =" + j);
//        System.out.println("i == j：" + (i == j) + "<-- 比较 -->i.equals(j):"+ i.equals(j));
//        System.out.println("<-128~127 以外的 Integer 值，Integer x = value; 的方式赋值！>");
//        Integer m = 128;
//        Integer n = 128;
//        System.out.println("m=" + m + ",n =" + n);
//        System.out.println("m == n：" + (m == n) + "<-- 比较 -->m.equals(n):"+ m.equals(n));
//        System.out.println();
//                System.out.println("<任意 Integer 值，Integer x = new Integer(value); 的方式赋值！>");
//        Integer x = new Integer(299);
//        Integer y = new Integer(299);
//        System.out.println("x=" + x + ",y =" + y);
//        System.out.println("x == y：" + (x == y) + "<-- 比较 -->x.equals(y):"+ x.equals(y));

        System.out.println(CAPACITY);
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);

    }


    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
}
