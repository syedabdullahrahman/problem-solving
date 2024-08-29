package com.abdullah.leetcode;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * User: Syed Abdullah
 * Date: 08-Aug-2022
 * Time: 12:51 AM
 * @See https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/submissions/1285644064/
 */
public class P_1296_Divide_Array_in_Sets_of_K_Consecutive_Numbers {
        public boolean isPossibleDivide(int[] hand, int groupSize) {
            if (hand.length % groupSize != 0) {
                return false;
            }
            Map<Integer, Integer> numMap = new TreeMap<>();
            for (int i = 0; i < hand.length; i++) {
                if (numMap.containsKey(hand[i])){
                    numMap.put(hand[i], numMap.get(hand[i])+1);
                } else {
                    numMap.put(hand[i],1);
                }
            }

            for (int i = 0; i < hand.length / groupSize; i++) {
                int groupMin = Collections.min(numMap.keySet());
                for (int j = 0, k = groupMin; j < groupSize; j++, k++) {
                    if(numMap.containsKey(k)) {
                        int occurrence = numMap.get(k);
                        numMap.put(k, --occurrence);
                        if(occurrence == 0) {
                            numMap.remove(k);
                        }
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
}

