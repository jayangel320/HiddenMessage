package com.company;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//When this runs only the Step two program runs
//if you want Step One program to run you can uncomment the code belode runToStart
public class Main {

    public static void main(String[] args) {

        //Given: The Star Wars Saga
        //  -_****_*___***_-_*-_*-*___*--_*-_*-*_***___***_*-_--*_*-

        //Remove: Yoda
        //  -*--_---_-**_*-

        //And Remove: Leia
        //  *-**_*_**_*-

        Message.runToStart();


        /*
        String input1 = "****_*_*-**_*-**_---___*--_---_*-*_*-**_-**";
        String input2 = "****_*_*-**_*--*";
        Message.HiddenMessage(input1,input2);
        */

    }
}
