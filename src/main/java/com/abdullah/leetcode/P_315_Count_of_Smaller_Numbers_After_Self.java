package com.abdullah.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Syed Abdullah
 * Date: 24-Jul-2022
 * Time: 1:14 PM
 */
public class P_315_Count_of_Smaller_Numbers_After_Self {
    class Node{
        int val;
        int leftCount;
        int selfCount;
        Node right, left;
        Node(int val){
            this.val = val;
            this.leftCount = 0;
            this.selfCount = 1;
            right = left = null;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        Node root = new Node(nums[nums.length-1]);
        List<Integer> result = new LinkedList<>();
        result.add(0);
        for (int i = nums.length-2; i >= 0; i--) {
            result.add(0,insert(root,nums[i]));
        }
        return result;
    }

    private Integer insert(Node node, int num) {
        if(node.val == num){
            node.selfCount++;
            return node.leftCount;
        } else if(num < node.val){
            node.leftCount++;
            if(node.left == null){
                node.left = new Node(num);
                return 0;
            } else {
                return insert(node.left,num);
            }
        } else {
            if(node.right == null){
                node.right = new Node(num);
                return node.selfCount+node.leftCount;
            } else {
                return node.selfCount+ node.leftCount + insert(node.right,num);
            }
        }
    }

    public static void main(String[] args) {
    }
}
