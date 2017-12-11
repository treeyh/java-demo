package com.ejyi.demo.guava;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class RangeDemo {

    public static void main(String args[]){
        RangeDemo tester = new RangeDemo();
        tester.testRange();
    }

    private void testRange(){

        //create a range [a,b] = { x | a <= x <= b}
        Range<Integer> range1 = Range.closed(0, 9);
        System.out.print("[0,9] : ");
        printRange(range1);
        System.out.println("5 is present: " + range1.contains(5));
        System.out.println("(1,2,3) is present: " + range1.containsAll(Ints.asList(1, 2, 3)));
        System.out.println("Lower Bound: " + range1.lowerEndpoint());
        System.out.println("Upper Bound: " + range1.upperEndpoint());

        //create a range (a,b) = { x | a < x < b}
        Range<Integer> range2 = Range.open(0, 9);
        System.out.print("(0,9) : ");
        printRange(range2);

        //create a range (a,b] = { x | a < x <= b}
        Range<Integer> range3 = Range.openClosed(0, 9);
        System.out.print("(0,9] : ");
        printRange(range3);

        //create a range [a,b) = { x | a <= x < b}
        Range<Integer> range4 = Range.closedOpen(0, 9);
        System.out.print("[0,9) : ");
        printRange(range4);

        //create an open ended range (9, infinity
        Range<Integer> range5 = Range.greaterThan(9);
        System.out.println("(9,infinity) : ");
        System.out.println("Lower Bound: " + range5.lowerEndpoint());
        System.out.println("Upper Bound present: " + range5.hasUpperBound());

        Range<Integer> range6 = Range.closed(3, 5);
        printRange(range6);

        //check a subrange [3,5] in [0,9]
        System.out.println("[0,9] encloses [3,5]:" + range1.encloses(range6));

        Range<Integer> range7 = Range.closed(9, 20);
        printRange(range7);
        //check ranges to be connected
        System.out.println("[0,9] is connected [9,20]:" + range1.isConnected(range7));

        Range<Integer> range8 = Range.closed(5, 15);

        //intersection
        printRange(range1.intersection(range8));

        //span
        printRange(range1.span(range8));
    }

    private void printRange(Range<Integer> range){
        System.out.print("[ ");
        for(int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade +" ");
        }
        System.out.println("]");
    }
}
