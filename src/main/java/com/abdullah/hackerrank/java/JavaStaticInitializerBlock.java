package com.abdullah.hackerrank.java;

import java.util.Scanner;

/**
 * Created On:  2:08 AM 07-Feb-22
 *
 * @author Syed Abdullah
 */
public class JavaStaticInitializerBlock {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int B = sc.nextInt();
        int H = sc.nextInt();
        if(B<=0 || H<=0){
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        } else{
            int area=B*H;
            System.out.print(area);
        }
    }
}
