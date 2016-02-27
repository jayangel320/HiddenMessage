package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        long startTime = System.currentTimeMillis();

        //String input1 = "*-_-***";
        //String input1 = "****_*_*-**_*-**_---___*--_---_*-*_*-**_-**";
        String input1 = "-_****_*___***_-_*-_*-*___*--_*-_*-*_***___***_*-_--*_*-";

        //String input2 = "*-*";
        //String input2 = "****_*_*-**_*--*";
        String input2 = "-*--_---_-**_*-";

        //String input3 = "*-*";
        String input3 = "*-**_*_**_*-";

        System.out.println("------");
        //Message.HiddenMessage(input1,input2);
        Message.HiddenMessage2(input1,input2,input3);
        System.out.println("-----");

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;


        System.out.println(totalTime);




    }
}
