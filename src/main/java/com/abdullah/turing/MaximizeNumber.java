package com.abdullah.turing;

/**
 * User: Syed Abdullah
 * Date: 13-May-2022
 * Time: 11:18 AM
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Statement: Maximize a number by swapping maximum one number
 * Input: 7236
 * Output: 7632
 */
public class MaximizeNumber {
    public static int maximizeTheNumber(int num){
        List<Integer> numbers = new ArrayList<>();
        while(num>0){
            numbers.add(num%10);
            num = num/10;
        }
        boolean swapped = false;
        int mostSignificantDigitPos = numbers.size();
        int largestDigit = Integer.MIN_VALUE;
        int largestDigitPos = 0;
        while(!swapped){
            mostSignificantDigitPos--;
            for (int i = 0; i < mostSignificantDigitPos; i++) {
                final Integer numberAtIndex = numbers.get(i);
                if(numberAtIndex >largestDigit){
                    largestDigit = numberAtIndex;
                    largestDigitPos = i;
                }
            }

            if (largestDigit > numbers.get(mostSignificantDigitPos)) {
                swapped = true;
                int temp = numbers.get(mostSignificantDigitPos);
                numbers.set(mostSignificantDigitPos,largestDigit);
                numbers.set(largestDigitPos, temp);
            }
        }

        for (int i = numbers.size()-1; i >=0 ; i--) {
            num = num*10 + numbers.get(i);
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(maximizeTheNumber(1236));
    }
}
