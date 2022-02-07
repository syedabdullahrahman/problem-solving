package com.abdullah.hackerrank.java;

import java.util.Scanner;

/**
 * Created On:  2:08 AM 07-Feb-22
 *
 * @author Syed Abdullah
 */
public class JavaStaticInitializerBlock {
    static int B,H;
    static boolean flag;
    static {
        Scanner sc = new Scanner(System.in);
        B = sc.nextInt();
        H = sc.nextInt();
        if(B<=0 || H<=0){
            System.out.println("java.lang.Exception: Breadth and height must be positive");
            flag = false;
        } else{
            flag = true;
        }
    }

    public static void main(String[] args){
        if(flag){
            int area=B*H;
            System.out.print(area);
        }
    }//end of main

}//end of class
