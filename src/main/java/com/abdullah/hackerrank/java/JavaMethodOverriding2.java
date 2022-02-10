package com.abdullah.hackerrank.java;

/**
 * Created On:  11:27 PM 10-Feb-22
 *
 * @author Syed Abdullah
 */

class BiCycle{
    String define_me(){
        return "a vehicle with pedals.";
    }
}

class MotorCycle extends BiCycle{
    String define_me(){
        return "a cycle with an engine.";
    }

    MotorCycle(){
        System.out.println("Hello I am a motorcycle, I am "+ define_me());

        String temp=super.define_me(); //Fix this line

        System.out.println("My ancestor is a cycle who is "+ temp );
    }

}

class Solution{
    public static void main(String []args){
        MotorCycle M=new MotorCycle();
    }
}
