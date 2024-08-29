package com.abdullah.leetcode;

import java.util.Arrays;

/**
 * Updated by Abdullah
 * Date: 29-Aug-24
 * Time: 6:08 PM
 *
 */
public class P_75_Sort_Colors {
        public void sortColors(int[] nums) {
            Arrays.sort(nums);
            for(int i:nums){
                System.out.println(i);
            }
        }
}
