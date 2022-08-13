package com.abdullah.leetcode;

/**
 * User: Syed Abdullah
 * Date: 13-Aug-2022
 * Time: 1:02 AM
 */
/**
 * Sliding Window technique
 */
public class P_121_best_time_to_buy_and_sell_stock {
    public int maxProfit(int[] prices) {
        int start=0, end=0;
        int max=0;

        while(start<prices.length && end<prices.length){
            if(prices[start]<prices[end]){
                max=Math.max(max,prices[end]-prices[start]);
                end++;
            } else {
                start=end++;
            }
        }
        return max;
    }
}
