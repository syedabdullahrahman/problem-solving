package com.abdullah.codility;

/**
 * User: Syed Abdullah
 * Date: 05-Apr-2022
 * Time: 3:29 AM
 * Problem Link: https://app.codility.com/programmers/trainings/9/binary_gap/
 */
public class BinaryGap {

    public int solution(int N) {
        int one_seen = 0;
        int max_bin_gap = 0;
        int bin_gap=0;
        while(N>0){
            int current_bit = N%2;
            if(current_bit == 1){
                one_seen++;
            } else {
                if(one_seen==1)
                    bin_gap++;
            }

            if(one_seen==2){
                if(bin_gap>max_bin_gap){
                    max_bin_gap = bin_gap;
                }
                bin_gap=0;
                one_seen--;
            }
            N = N/2;
        }
        return max_bin_gap;
    }
}
