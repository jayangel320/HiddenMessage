package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jayangel320 on 2/25/16.
 */


public class Message {

    public static void runToStart(){


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = "";
        String input2 = "";
        String input3 = "";

        try {
            System.out.printf("Enter in the first message: ");
            input1 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.printf("Enter in the first hidden message: ");
            input2 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.printf("Enter in the first hidden message: ");
            input3 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(input1 != "" && input2 != "" && input3 != "") {
            //timer!!
            long startTime = System.currentTimeMillis();
            HiddenMessage2(input1, input2, input3);
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.printf(" %d seconds.%n", totalTime/1000);
            System.out.println("--------");
        }


    }

    public static void HiddenMessage(String input1, String input2) {

        //converts message into char array
        char[] parsedInput1 = input1.toCharArray();

        //converts hidden message into char array
        char[] parsedInput2 = input2.toCharArray();

        //finds number of elements in each solution
        int rows = parsedInput2.length;

        //finds the number of solutions there will be
        int columns = numOfDeletionPaths(parsedInput1, parsedInput2, 0, 0, 0);

        //creates blank array of solutions
        int[][] solutions = blankArray(rows, columns);

        //fills in solution array
        DeletionPaths(parsedInput1, parsedInput2, 0, 0, solutions);

        //fills in blank elements in solution array
        fixArray(solutions, rows);

        //prints out total number of unique solutions
        System.out.printf("There are %d unique solutions %n", testSolutions(parsedInput1, solutions, rows, columns));
    }


    //This method creates and returns an array filled with -1's
    //that will contain the indices of the input array that can be removed
    private static int[][] blankArray(int rows, int columns) {

        //creates blank array
        int[][] solutions = new int[rows][columns];

        //Fills in array with -1's since 0's exist as an array index
        for (int i = 1; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                solutions[j][i] = -1;
            }
        }
        try {
            //This element in the array will keep track of which row to add values to
            solutions[1][0] = 1;
        }catch(ArrayIndexOutOfBoundsException aiob){
            System.out.printf("%d %d",rows,columns);
        }
        //This element will contain the max amount of columns that can be filled in
        solutions[0][0] = columns - 1;

        //returns array
        return solutions;

    }

    //this will count the number of deletion paths but will not create or change any char[]
    private static int numOfDeletionPaths(char[] array1, char[] array2, int array1Start,
                                         int array2Start, int solution) {

        //makes sure we comparing valid indexes for array1 and array2
        if (array1Start < array1.length && array2Start < array2.length) {

            //Cycle through array1
            for (int tmp = array1Start; tmp < array1.length; tmp++) {

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


    //this will fill the solutions array with indices of the array1 that can be removed
    //each column will be a different solution
    private static void DeletionPaths(char[] array1, char[] array2, int array1Start, int array2Start, int[][] solutions) {
        //makes sure we comparing valid indexes for array1 and array2
        if (array1Start < array1.length && array2Start < array2.length) {
            //Cycle through array1
            for (int tmp = array1Start; tmp < array1.length; tmp++) {
                //compare the arrays elements
                if (array1[tmp] == array2[array2Start]) {

                    //this makes sure we are only adding values to columns that exist
                    if (solutions[1][0] < solutions[0][0]) {
                        //adding the indices to the solutions array
                        solutions[array2Start][solutions[1][0]] = tmp;
                    }
                    //if the array2start is the at the end of array2 then we have a found a valid solution
                    if (array2Start == array2.length - 1) {
                        //increments column
                        solutions[1][0]++;
                    }
                    //we need to recursively check the next element in array2 with the rest of array1 starting
                    // at the next element in array1
                    //solution is an int value of how many valid deletion paths there are
                    DeletionPaths(array1, array2, tmp + 1, array2Start + 1, solutions);
                }
            }
        }
    }

    //The way the solutions array is filled is the row that is first filled in in a column is the first sequential
    // row that differs from the previous column. This method copies and pastes the repeated values from previous column
    // into the empty values in the current column which are marked by -1's
    private static void fixArray(int[][] array, int rows) {
        //provides the other dimension to the array
        int columns = array[0].length;

        //cycles through the columns and rows sequentialy and copies from the previous column if a -1 is found
        for (int i = 1; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (array[j][i] == -1) {
                    array[j][i] = array[j][i - 1];
                }
            }
        }
    }

    //Takes the original input message and single solution of indices that can be removed and
    //removes them. It returns a shorten input in the form of a string
    private static String deleteWords(char[] input, int[] indexList) {

        //Length of the single solution array
        int length = indexList.length;
        //the current index of the solution array
        int indexCounter = 0;
        //the counter for the for loop
        int tokenCounter = 0;
        //the index for the shortened input
        int outPutCounter = 0;
        //The length of the shortened input
        int limit = input.length - indexList.length;
        //the shortened input array
        char[] output = new char[limit];

        //Cycles through the input array
        for (char token : input) {
            // checks if the current index of the loop matches the index in the soluton array
            if (tokenCounter == indexList[indexCounter]) {
                //only increments the index counter provided it hasn't reached its max value
                if (indexCounter < length - 1) {
                    indexCounter++;
                }
            } else {
                //adds values to shortened input if index doesn't match the solutiona array value
                if (outPutCounter < limit) {
                    output[outPutCounter++] = token;
                }
            }
            //increments loop counter
            tokenCounter++;
        }
        //returns shortened input by converting it into a String
        return charToString(output);
    }


    //Cycles through the solutions column by column, deletes the corresponding indices, adds them to a Set
    //Then returns the Sets size
    private static int testSolutions(char[] input, int[][] solutions, int rows, int limit) {
        //Creates the set
        Set<String> uniqueSol = new HashSet<>();
        //Cycles through the columns
        for (int counter1 = 1; counter1 < limit; counter1++) {
            //creates a one dimensional array that will be filled in with a single solution from solutions array
            int[] solution = new int[rows];
            // copies a column from solutions into new int array
            for (int counter2 = 0; counter2 < rows; counter2++) {
                solution[counter2] = solutions[counter2][counter1];
            }
            //adds shortened inout to the set
            uniqueSol.add(deleteWords(input, solution));

        }
        //since values in Sets are unique the size of the set will give the correct amount of unique solutions
        return uniqueSol.size();
    }

    //Converts the char array into a String into the format that is needed
    private static String charToString(char[] input) {

        //creates blank String
        String tmpLetter = "";
        //adds each char from array to the String
        for (char token : input) {
            tmpLetter += token;
        }
        //returns newly made String
        return tmpLetter;

    }


    //Cycles through the solutions column by column, deletes the corresponding indices, adds them to a Set
    //Then returns the Set itself
    private static Set<String> testSolutions2(char[] input, int[][] solutions, int rows, int limit) {
        //Creates the set
        Set<String> uniqueSol = new HashSet<>();
        //Cycles through the columns
        for (int counter1 = 1; counter1 < limit; counter1++) {
            //creates a one dimensional array that will be filled in with a single solution from solutions array
            int[] solution = new int[rows];
            // copies a column from solutions into new int array
            for (int counter2 = 0; counter2 < rows; counter2++) {
                solution[counter2] = solutions[counter2][counter1];
            }
            //adds shortened inout to the set
            uniqueSol.add(deleteWords(input, solution));

        }
        //since values in Sets are unique the size of the set will give the correct amount of unique solutions
        return uniqueSol;
    }

    private static void HiddenMessage2(String input1, String input2, String input3) {

        //turn input1 into char array
        char[] parsedInput1 = input1.toCharArray();

        //turn input2 into char array
        char[] parsedInput2 = input2.toCharArray();

        //find amount of elements in each solution
        int rows = parsedInput2.length;

        //find the amount of solutions
        int columns = numOfDeletionPaths(parsedInput1, parsedInput2, 0, 0, 0);

        //create blank solution array
        int[][] solutions = blankArray(rows, columns);

        //fill in solution array using first hidden message
        DeletionPaths(parsedInput1, parsedInput2, 0, 0, solutions);

        //Make the output pretty
        System.out.println("--------");

        //fills in blank elements in solution array
        fixArray(solutions, rows);

        //finds the unique solutions
        Set<String> inBetween = testSolutions2(parsedInput1, solutions, rows, columns);

        //--------------------2nd Hidden Message-------------------------

        //converts second hidden message to char array
        char[] parsedInput3 = input3.toCharArray();

        //finds length of new solutions
        rows = parsedInput3.length;

        //creates the set that will contain all the unique final solutions
        Set<String> finalSolutions = new HashSet<>();

        //loops through solutions from first hidden message and repeats previous steps using second hidden message
        // on each
        for (String sSolution : inBetween) {

            //find the amount of solutions
            columns = numOfDeletionPaths(sSolution.toCharArray(), parsedInput3, 0, 0, 0);

            //makes sure there were solutions for this String
            if(columns > 0) {
                //creates blank array of solutions
                solutions = blankArray(rows, columns);

                //fills in blank array with solutions
                DeletionPaths(sSolution.toCharArray(), parsedInput3, 0, 0, solutions);

                //fills in blank elements in solution array
                fixArray(solutions, rows);

                //removes the second message from each String and adds it to the final solution array
                finalSolutions.addAll(testSolutions2(sSolution.toCharArray(), solutions, rows, columns));
            }
        }
        //prints out total number of unique solutions
        System.out.printf("There are %d unique remaining sequences calculated in", finalSolutions.size());

    }
}


