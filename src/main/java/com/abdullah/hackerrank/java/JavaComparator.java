package com.abdullah.hackerrank.java;

/**
 * Created On:  12:05 AM 11-Feb-22
 *
 * @author Syed Abdullah
 */

/**
 * Comparators are used to compare two objects. In this challenge, you'll create a comparator and use it to sort an array.
 */
import java.util.*;

// Write your Checker class here

class Player{
    String name;
    int score;

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }
}

class Checker implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if(Integer.compare(o1.score ,o2.score )== 0)
        {
            return o1.name.compareTo(o2.name);
        } else {
            return -1 * Integer.compare(o1.score,o2.score);
        }
    }
}

class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();

        for(int i = 0; i < n; i++){
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }
}
