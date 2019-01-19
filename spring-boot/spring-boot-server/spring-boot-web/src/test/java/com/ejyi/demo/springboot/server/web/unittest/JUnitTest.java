package com.ejyi.demo.springboot.server.web.unittest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;

/**
 * @author tree
 * @version 1.0
 * @description Junit单元测试示例
 * @create 2019-01-17 8:26 PM
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class JUnitTest {

    private static int num = 0;

    @Test
    public void test1(){
        System.out.println("test1 run...");
        System.out.println("test1 num = " + num);
        num = 1;
        System.out.println("test1 num = " + num);
    }

    @Test
    public void test2(){
        System.out.println("test2 run...");
        System.out.println("test2 num = " + num);
        num = 2;
        System.out.println("test2 num = " + num);
    }

    @Test
    public void test3(){
        System.out.println("test3 run...");
        System.out.println("test3 num = " + num);
        num = 3;
        System.out.println("test3 num = " + num);
    }

    @Test
    public void test4(){
        System.out.println("test4 run...");
        System.out.println("test4 num = " + num);
        num = 4;
        System.out.println("test4 num = " + num);

        Long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        Date date2 = new Date(time);

        Assert.assertEquals(date1, date2);
        Assert.assertTrue(date1 == date2);
    }

    @Before
    public void beforeEveryTest(){
        System.out.println("=== beforeEveryTest ===");
    }

    @After
    public void afterEveryTest(){
        System.out.println("=== afterEveryTest ===");
    }

    // must be static
    @BeforeClass
    public static void beforeClassTest(){
        System.out.println("===beforeClassTest===");
    }

    // must be static
    @AfterClass
    public static void afterClassTest(){
        System.out.println("===afterClassTest===");
    }


}
