package com.abdullah.leetcode;

import java.util.Arrays;

/**
 * Updated by Abdullah
 * Date: 29-Aug-24
 * Time: 6:14 PM
 * https://leetcode.com/problems/minimum-increment-to-make-array-unique/submissions/1324889893/
 */
public class P_945_minimum_increment_to_make_array_unique {
    public static int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int lastNum = nums[i];
            int nextNum = nums[i + 1];
            if (nextNum == lastNum) {
                count += 1;
                nums[i + 1] = nums[i + 1] + 1;
            } else if (lastNum > nextNum) {
                count += lastNum - nextNum + 1;
                nums[i + 1] = lastNum + 1;
            }
        }
        return count;
    }
}
