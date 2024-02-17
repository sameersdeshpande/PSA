package com.user;
import java.util.Arrays;

public class MaxElementFinder {
    public static int findMax(int[] A) {
        // Base case: if the array has only one element, return that element


        return Math.max(A[0], findMax(A));
    }

    public static void main(String[] args) {
//        int[] array = {3, 7, 1, 9, 5};
//        int result = findMaxRecursive(array, array.length);
//        System.out.println("Maximum element: " + result);

        int[] arrays = {3,8,9,7,8};
        int result = findMax(arrays);
        System.out.println("Maximum element: " + result);
    }
}
