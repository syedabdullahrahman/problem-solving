package com.abdullah.leetcode;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Updated by Abdullah
 * Date: 29-Aug-24
 * Time: 6:07 PM
 */

public class P_846_Hand_of_Straights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        Map<Integer, Integer> numMap = new TreeMap<>();
        for (int i = 0; i < hand.length; i++) {
            if (numMap.containsKey(hand[i])) {
                numMap.put(hand[i], numMap.get(hand[i]) + 1);
            } else {
                numMap.put(hand[i], 1);
            }
        }

        for (int i = 0; i < hand.length / groupSize; i++) {
            int groupMin = Collections.min(numMap.keySet());
            for (int j = 0, k = groupMin; j < groupSize; j++, k++) {
                if (numMap.containsKey(k)) {
                    int occurrence = numMap.get(k);
                    numMap.put(k, --occurrence);
                    if (occurrence == 0) {
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
