package com.ejyi.demo.guava;


import com.google.common.base.Optional;

/**
 * http://www.yiibai.com/guava/guava_optional_class.html#
 *
 *
 *
 */
public class OptionalDemo {

    public static void main(String args[]){
        OptionalDemo guavaTester = new OptionalDemo();

        Integer value1 =  null;
        Integer value2 =  new Integer(10);

        //Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);
        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);

        System.out.println(guavaTester.sum(a,b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());

        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }


}
