package com.ejyi.demo.springboot.base1.helper;

public class RandomHelper {
    public static final Integer randomInt(Integer min, Integer max){
        Integer temp = max - min + 1;
        Double t = Math.random() * temp;
        return (int)Math.floor(t) + min;
    }
    public static final Long randomLong(Long min, Long max){
        Long temp = max - min + 1;
        Double t = Math.random() * temp;
        return (int)Math.floor(t) + min;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomHelper.randomLong(10L,20L));
        }
    }
}

