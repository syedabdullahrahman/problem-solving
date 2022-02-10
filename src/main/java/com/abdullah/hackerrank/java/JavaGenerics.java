package com.abdullah.hackerrank.java;

import java.lang.reflect.Method;
/**
 * Created On:  11:44 PM 10-Feb-22
 *
 * @author Syed Abdullah
 */

/**
 * Generic methods are a very efficient way to handle multiple datatypes using a single method. This problem will test your knowledge on Java Generic methods.
 */

class Printer {
    public <T> void printArray(T[] arrray) {
        for (T item : arrray) {
            System.out.println(item.toString());
        }
    }
    //Write your code here

}

public class JavaGenerics {

    public static void main( String args[] ) {
        Printer myPrinter = new Printer();
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);
        int count = 0;

        for (Method method : Printer.class.getDeclaredMethods()) {
            String name = method.getName();

            if(name.equals("printArray"))
                count++;
        }

        if(count > 1)
            System.out.println("Method overloading is not allowed!");

    }
}