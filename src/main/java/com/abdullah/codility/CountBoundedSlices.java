package com.abdullah.codility;

/**
 * User: Syed Abdullah
 * Date: 07-Apr-2022
 * Time: 1:30 AM
 *
 * Hints: this problem uses Segment Tree
 * Problem Link: https://app.codility.com/programmers/trainings/7/count_bounded_slices/
 * Solution Status: https://app.codility.com/demo/results/trainingFEXKR3-FK6/
 */

/**
 * Provides interface to perform operations on range queue like sum or min
 */
interface Operation{
    int perform(int a, int b);
    int outOfRangeNotation();
}

class MinOperation implements Operation{
    @Override
    public int perform(int a, int b){
        return Math.min(a,b);
    }

    public int outOfRangeNotation(){
        return Integer.MAX_VALUE;
    }

}

class MaxOperation implements Operation{
    @Override
    public int perform(int a, int b){
        return Math.max(a,b);
    }
    public int outOfRangeNotation(){
        return Integer.MIN_VALUE;
    }

}


class SegmentTree{
    private int[] tree;
    private int[] inputArray;
    private final Operation operation;
    SegmentTree(int[] inputArray, Operation operation){
        this.inputArray = inputArray;
        this.operation = operation;
        int inputArrayLength = inputArray.length;
        tree = new int[inputArrayLength*3];

        init(0,0,inputArrayLength-1);
    }
    private void init(int node,int leftIndex,int rightIndex){
        if(leftIndex == rightIndex){
            tree[node] = inputArray[leftIndex];
            return;
        }
        int left = node*2+1;
        int right = node*2+2;
        int mid = (rightIndex+leftIndex)/2;
        init(left,leftIndex,mid);
        init(right,mid+1,rightIndex);
        tree[node] = operation.perform(tree[left], tree[right]);
    }
    public int query(int leftIndex,int rightIndex){
        return _query(0,0,inputArray.length-1,leftIndex,rightIndex);
    }

    private int _query(int node, int left, int right, int queryLeft, int queryRight) {
        if(right<queryLeft || left > queryRight ){
            return operation.outOfRangeNotation();
        }
        if(left>=queryLeft && right<=queryRight){
            return tree[node];
        }
        int leftNode = node*2+1;
        int rightNode = node*2+2;
        int midNode = (left+right)/2;
        int leftTreeResult = _query(leftNode,left,midNode,queryLeft,queryRight);
        int rightTreeResult = _query(rightNode,midNode+1,right,queryLeft,queryRight);
        return operation.perform(leftTreeResult, rightTreeResult);
    }
    private void update(int index, int value){
        if(index>=0 && index<inputArray.length){
            inputArray[index] = value;
            _update(0,0,inputArray.length-1,index,value);
        }else {
            throw new IndexOutOfBoundsException();
        }

    }

    private void _update(int node, int left, int right, int index, int value) {
        if(left==index && right ==index){
            tree[node] = value;
            return;
        }
        if(index<left || index>right){
            return;
        }
        int leftNode = node*2+1;
        int rightNode = node*2+2;
        int mid = (left+right)/2;
        _update(leftNode,left,mid,index,value);
        _update(rightNode,mid+1,right,index,value);
        tree[node] = operation.perform(tree[left], tree[right]);
    }
}

public class CountBoundedSlices {
    public int solution(int K, int[] A) {
        SegmentTree maxTree = new SegmentTree(A, new MaxOperation());
        SegmentTree minTree = new SegmentTree(A, new MinOperation());

        int total = 0;
        int j=0;
        for(int i=0;i<A.length;i++){
            while (j<A.length){
                if((maxTree.query(i,j)-minTree.query(i,j))<=K){
                    j++;
                }else{
                    break;
                }
            }
            total+= (j-i);
        }
        return total;
    }
}