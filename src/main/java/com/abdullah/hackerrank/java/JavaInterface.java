package com.abdullah.hackerrank.java;

/**
 * Created On:  9:50 PM 10-Feb-22
 *
 * @author Syed Abdullah
 */


import java.util.*;
import java.util.stream.IntStream;


/**
 * A Java interface can only contain method signatures and fields. The interface can be used to achieve polymorphism. In this problem, you will practice your knowledge on interfaces.
 */


//Write your code here
interface AdvancedArithmetic{
    int divisor_sum(int n);
}

class MyCalculator implements AdvancedArithmetic{

    @Override
    public int divisor_sum(int n) {
        /*int sum = 0;
        for (int i = 1; i <= n; i++) {
            if(n%i==0){
                sum+=i;
            }
        }
        return sum;*/
        return IntStream.rangeClosed(1,n).filter(i -> n%i==0).sum();
    }
}

public class JavaInterface {
    public static void main(String []args){
        MyCalculator my_calculator = new MyCalculator();
        System.out.print("I implemented: ");
        ImplementedInterfaceNames(my_calculator);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long startTime = System.nanoTime();
        System.out.print(my_calculator.divisor_sum(n) + "\n");
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        sc.close();
    }
    /*
     *  ImplementedInterfaceNames method takes an object and prints the name of the interfaces it implemented
     */
    static void ImplementedInterfaceNames(Object o){
        Class[] theInterfaces = o.getClass().getInterfaces();
        for (int i = 0; i < theInterfaces.length; i++){
            String interfaceName = theInterfaces[i].getName();
            System.out.println(interfaceName);
        }
    }
}
