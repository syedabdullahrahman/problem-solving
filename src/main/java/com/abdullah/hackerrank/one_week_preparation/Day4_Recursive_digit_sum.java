package com.abdullah.hackerrank.one_week_preparation;

/**
 * Updated by Abdullah
 * Date: 03-Oct-24
 * Time: 10:31 PM
 */
//public class Day4_Recursive_digit_sum {
//    public static int superDigit(String n, int k) {
//        if(n.length() == 1) {
//            return Integer.parseInt(n);
//        } else{
//            Long sum =0l;
//            for(char c : n.toCharArray()) {
//                sum += Long.parseLong(Character.toString(c));
//            }
//        }
//    }
//
//}


class ReverseNumber {
    public static int reverse(int number) {

        String numString = Integer.toString(number);

        String reversedString = new StringBuilder(numString).reverse().toString();

        int reversedNumber = Integer.parseInt(reversedString);

        return reversedNumber;
    }

    public static void main(String[] args) {
        int number = 12345;
        int reversedNumber = reverse(number);
        System.out.println("Original number: " + number);
        System.out.println("Reversed number: " + reversedNumber);
    }
}
