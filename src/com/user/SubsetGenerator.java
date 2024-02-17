package com.user;

import java.util.ArrayList;
import java.util.List;

public class SubsetGenerator {


    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsetsHelper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsetsHelper(int[] nums, int index, List<Integer> currentSubset, List<List<Integer>> result) {
        // Add the current subset to the result
        result.add(new ArrayList<>(currentSubset));

        // Explore subsets by including or excluding the current element
        for (int i = index; i < nums.length; i++) {
            // Include the current element
            currentSubset.add(nums[i]);

            // Recursively generate subsets with the current element included
            generateSubsetsHelper(nums, i + 1, currentSubset, result);

            // Exclude the current element (backtrack)
            currentSubset.remove(currentSubset.size() - 1);
        }
    }




    public static void main(String[] args){
        int[] set = {1,2,3};
        List<List<Integer>> subsets = generateSubsets(set);

        for(List<Integer> subset: subsets){
            System.out.print(subset);
        }
    }

}
