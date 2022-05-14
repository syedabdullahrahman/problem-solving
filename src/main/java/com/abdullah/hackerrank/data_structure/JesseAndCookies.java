package com.abdullah.hackerrank.data_structure;

import java.io.*;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * User: Syed Abdullah
 * Date: 09-May-2022
 * Time: 10:32 PM
 */

/**
 * Note: In Hacker Rank: Java8+ got TLE for this solution, but Accepted for Java7
  */

public class JesseAndCookies {
    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        Queue<Integer> cookiesQueue = new PriorityQueue<>(A);
        Integer iteration = 0;
        while (cookiesQueue.peek() < k) {
            if (cookiesQueue.size() >= 2) {
                int LeastSweetCookie = cookiesQueue.poll();
                int SecondLeastSweetCookie = cookiesQueue.poll();
                cookiesQueue.add(LeastSweetCookie + 2 * SecondLeastSweetCookie);
                iteration++;
            } else {
                break;
            }
        }

        if (cookiesQueue.peek() < k) {
            return -1;
        } else {
            return iteration;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = JesseAndCookies.cookies(k, A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
