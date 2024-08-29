package com.abdullah.leetcode;

/**
 * Updated by Abdullah
 * Date: 29-Aug-24
 * Time: 6:11 PM
 *
 * https://leetcode.com/problems/minimum-falling-path-sum/submissions/1288301904/
 */
public class P_931_minimum_falling_path_sum {
    public int minFallingPathSum(int[][] matrix) {
        int MAX_VALUE = 999999;
        int[][] minSum = new int[matrix.length][matrix.length];

        for (int i = 0; i < minSum.length; i++) {
            for (int j = 0; j < minSum[i].length; j++) {
                minSum[i][j] = MAX_VALUE;
            }
        }

        for (int i = 0; i < minSum.length; i++) {
            minSum[0][i] = matrix[0][i];
        }

        for (int i = 1; i < minSum.length; i++) {
            for (int j = 0; j < minSum[i].length; j++) {
                minSum[i][j] = matrix[i][j] + Math.min(getMin(i - 1, j - 1, minSum), Math.min(getMin(i - 1, j, minSum),
                        getMin(i - 1, j + 1, minSum)));
            }
        }
        int min = MAX_VALUE;

        for (int i = 0; i < minSum.length; i++) {
            min = Math.min(min, minSum[minSum.length - 1][i]);
        }
        return min;
    }

    int getMin(int i, int j, int[][] minSum) {
        int MAX_VALUE = 999999;
        if (i >= 0 && j >= 0 && i < minSum.length && j < minSum.length) {
            return minSum[i][j];
        } else {
            return MAX_VALUE;
        }
    }
}
