package com.user;

public class ArrayToSumCalculator {

public static int computeTwoDArray(int[][] array){
    return computeArraySumHelper(array, 0, 0);
}
    private static int computeArraySumHelper(int[][] array, int row, int col) {
        // Base case: if we have reached the last row, return 0
        if (row == array.length) {
            return 0;
        }

        // If the end of the current row is reached, move to the next row
        if (col == array[row].length) {
            return computeArraySumHelper(array, row + 1, 0);
        }

        // Recursive case:
        // 1. Add the current element to the sum
        // 2. Move to the next column
        int currentElement = array[row][col];
        int sum = currentElement + computeArraySumHelper(array, row, col + 1);

        return sum;
    }



    public static void main(String[] args){
        int[][] twodarray = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int result = computeTwoDArray(twodarray);
        System.out.println("Sum of all elements: " + result);
    }
}
