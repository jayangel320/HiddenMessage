package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jaybob320 on 2/25/16.
 */
public class Message {

    public static String[] inputParse(String input){

        return input.split("_");

    }



    public static void HiddenMessage(String input1, String input2){

        char[] parsedInput1 = input1.toCharArray();

        char[] parsedInput2 = input2.toCharArray();

        int rows = parsedInput2.length;

        int columns = numOfDeletionPaths(parsedInput1,parsedInput2,0,0,0);

        int[][] solutions = new int[rows][8];

        DeletionPaths(parsedInput1,parsedInput2,0,0,solutions);

        fixArray(solutions,rows);

        System.out.printf("%d %n",testSolutions(parsedInput1,solutions,rows,columns));
    }


    //this will count the number of deletion paths but will not create or change any char[]
    public static int numOfDeletionPaths(char[] array1,char[] array2, int array1Start,
                                         int array2Start, int solution){

        //makes sure we comparing valid indexes for array1 and array2
        if(array1Start < array1.length && array2Start < array2.length){

            //Cycle through array1
            for(int tmp = array1Start;tmp < array1.length;tmp++) {

                //compare the arrays elements
                if (array1[tmp] == array2[array2Start]) {

                    //if the array2start is the at the end of array2 then we have a found a valid solution
                    if (array2Start == array2.length - 1) {

                        //increment and return value
                        solution++;
                    }

                    //we need to recursively check the next element in array2 with the rest of array1 starting
                    // at the next element in array1
                    //solution is an int value of how many valid deletion paths there are
                    solution = numOfDeletionPaths(array1, array2, tmp + 1, array2Start + 1, solution);

                }
            }
        }

        return solution;
    }

    //this will count the number of deletion paths but will not create or change any char[]
    public static void DeletionPaths(char[] array1,char[] array2, int array1Start,
                                         int array2Start,int[][] solutions){

        //makes sure we comparing valid indexes for array1 and array2
        if(array1Start < array1.length && array2Start < array2.length){

            //Cycle through array1
            for(int tmp = array1Start;tmp < array1.length;tmp++) {


               //compare the arrays elements
               if (array1[tmp] == array2[array2Start]) {

                   solutions[array2Start][solutions[1][0]] = tmp;

                   //if the array2start is the at the end of array2 then we have a found a valid solution
                   if (array2Start == array2.length - 1) {

                       //increments column
                        solutions[1][0]++;

                       //resets row to zero
                       solutions[0][0] = 0;
                   }

                   //we need to recursively check the next element in array2 with the rest of array1 starting
                   // at the next element in array1
                   //solution is an int value of how many valid deletion paths there are
                   DeletionPaths(array1, array2, tmp + 1, array2Start + 1,solutions);

               }
            }
        }

        return;
    }

    public static void fixArray(int[][] array, int rows){
        int columns = array[0].length;

        for(int i = 1; i < columns; i++){
            for(int j = 0; j < rows-1; j++){
                if(array[j][i] == array[j+1][i]){
                    array[j+1][i] = array[j+1][i-1];
                }
            }
        }
    }

    public static String deleteWords(char[] input,int[] indexList){
        int length = indexList.length;
        int indexCounter = 0;
        int tokenCounter = 0;
        int outPutCounter = 0;
        char[] output = new char[input.length - indexList.length];

        for(char token: input){
            //System.out.printf("%d %d %d %n",indexCounter,tokenCounter,outPutCounter);
            if(tokenCounter == indexList[indexCounter])
            {
                if(indexCounter < length - 1) {
                    indexCounter++;
                }
            }
            else{

                output[outPutCounter++] = token;
            }

            tokenCounter++;
        }
        return charToString(output);
    }


    public static int testSolutions(char[] input, int[][] solutions, int rows,int limit){

        Set<String> uniqueSol = new HashSet<>();

        for(int counter1 = 1;counter1 < limit;counter1++){

            int[] solution = new int[rows];

            for(int counter2 = 0; counter2 < rows;counter2++){
                solution[counter2] = solutions[counter2][counter1];
            }

            uniqueSol.add(deleteWords(input,solution));

        }
        return uniqueSol.size();
    }

    public static String charToString(char[] input){

        String tmpLetter = "";
        for(char token: input){
            tmpLetter += token;
        }
        return tmpLetter;

    }




}
