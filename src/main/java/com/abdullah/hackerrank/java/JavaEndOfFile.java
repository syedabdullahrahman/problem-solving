package com.abdullah.hackerrank.java;

import java.util.Scanner;

/**
 * Created On:  2:08 AM 07-Feb-22
 *
 * @author Syed Abdullah
 */
public class JavaEndOfFile {
    public static void main(String[] argh) {
        Scanner sc = new Scanner(System.in);
        int lineNumber = 0;
        while (sc.hasNext()){
            System.out.println(++lineNumber +" "+sc.nextLine());
        }
    }
}
