package com.abdullah.leetcode;

/**
 * User: Syed Abdullah
 * Date: 08-Aug-2022
 * Time: 12:51 AM
 * @See https://leetcode.com/problems/maximum-subarray/
 * @See https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
public class P_53_Maximum_Subarray {
    public int maxSubArray(int[] nums) {
        int max_sum = -9999999;
        int current_sum = 0;
        for(int i:nums){
            current_sum = current_sum+i ;
            max_sum = Math.max(max_sum,current_sum);
            if(current_sum<0){
                current_sum=0;
            }
        }
        return max_sum;
    }
    static public void test(String args[]){

    }
}
