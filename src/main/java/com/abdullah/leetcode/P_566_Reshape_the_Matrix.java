package com.abdullah.leetcode;

/**
 * User: Syed Abdullah
 * Date: 13-Aug-2022
 * Time: 1:56 PM
 */

/**
 * Matrix address calculation
 */
public class P_566_Reshape_the_Matrix {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int oldRow = mat.length;
        int oldCol = mat[0].length;
        int res[][] = new int[r][c];

        int totalNums = oldRow*oldCol;

        if(totalNums != r*c){
            return mat;
        }
        for (int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                int loc = (i*c)+(j+1);
                int calR = ((int) Math.ceil((double)loc/oldCol))-1;
                res[i][j] = mat[calR][loc-calR*oldCol-1];
            }
        }
        return res;
    }
}
