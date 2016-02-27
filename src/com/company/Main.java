package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        long startTime = System.currentTimeMillis();

        String input1 = "*-_-***";

        String input2 = "*-*";
        System.out.println("------");
        Message.HiddenMessage(input1,input2);


        System.out.println("-----");

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;


        System.out.println(totalTime);




    }
}
