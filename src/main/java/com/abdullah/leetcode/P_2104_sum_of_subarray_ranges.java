package com.abdullah.leetcode;

/**
 * Updated by Abdullah
 * Date: 29-Aug-24
 * Time: 6:13 PM
 * https://leetcode.com/problems/sum-of-subarray-ranges/submissions/1288434834/
 */
public class P_2104_sum_of_subarray_ranges {
        private static final int MAX_VALUE = Integer.MAX_VALUE;
        private static final int MIN_VALUE = Integer.MIN_VALUE;

        private static int getValue(int i, int j, int[][] arr, int defaultValue) {
            if (i >= 0 && j >= 0 && i < arr.length && j < arr.length) {
                return arr[i][j];
            } else {
                return defaultValue;
            }
        }

        public static int getMin(int i, int j, int[][] arr) {
            return getValue(i, j, arr, MAX_VALUE);
        }

        public static int getMax(int i, int j, int[][] arr) {
            return getValue(i, j, arr, MIN_VALUE);
        }

        public static long subArrayRanges(int[] nums) {
            int MIN = Integer.MIN_VALUE;
            int MAX = Integer.MAX_VALUE;
            int[][] min = new int[nums.length][nums.length];
            int[][] max = new int[nums.length][nums.length];

            for (int i=0;i< nums.length;i++){
                for (int j=0; j< nums.length; j++){
                    min[i][j] = MAX;
                }
            }
            for (int i=0;i< nums.length;i++){
                for (int j=0; j< nums.length; j++){
                    max[i][j] = MIN;
                }
            }

            for (int i=0;i< nums.length;i++){
                for (int j=i; j< nums.length; j++){
                    min[i][j] = Math.min(nums[j], getMin(i,j-1,min));
                }
            }

            for (int i=0;i< nums.length;i++){
                for (int j=i; j< nums.length; j++){
                    max[i][j] = Math.max(nums[j], getMax(i,j-1,max));
                }
            }

//        for (int i=0;i< nums.length;i++){
//            for (int j=i; j< nums.length; j++){
//                System.out.print(max[i][j]+ " - ");
//            }
//            System.out.println();
//        }
//
//        for (int i=0;i< nums.length;i++){
//            for (int j=i; j< nums.length; j++){
//                System.out.print(min[i][j]+ " - ");
//            }
//            System.out.println();
//        }


            long sum = 0;
            for (int i=0;i< nums.length;i++){
                for (int j=i+1; j< nums.length; j++){
                    sum += (max[i][j] - min[i][j]);
                }
            }
            return sum;
        }
}
