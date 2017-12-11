package com.ejyi.demo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LoadingCacheDemo {

    public static void main(String args[]){
        //create a cache for employees based on their employee id
        LoadingCache employeeCache =
                CacheBuilder.newBuilder()
                        .maximumSize(100) // maximum 100 records can be cached
                        .expireAfterAccess(30, TimeUnit.MINUTES) // cache will expire after 30 minutes of access
                        .build(new CacheLoader<String, Employee>(){
                            @Override
                            public Employee load(String empId) throws Exception {
                                //make the expensive call
                                return getFromDatabase(empId);
                            }
                        });

        try {
            //on first invocation, cache will be populated with corresponding
            //employee record
            System.out.println("Invocation #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));
            //second invocation, data will be returned from cache
            System.out.println("Invocation #2");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Employee getFromDatabase(String empId){
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map<String, Employee> database = new HashMap<>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
        System.out.println("Database hit for" + empId);
        return database.get(empId);
    }
}
