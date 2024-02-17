package com.user;

public class BinarySearch {



    public static boolean binarysearch(int[] arr, int tar){
        return binarysearch(arr, tar, 0, arr.length-1);
    }
    public static boolean binarysearch(int[] arr, int tar, int left, int right){
        if (left > right) {
            return false;
        }
    int mid = (left +right)/2;

    if(arr[mid]==tar){
        return true;
    }
    else if (arr[mid]<tar){
        return binarysearch(arr, tar, mid+1,right);
    }
    else if(arr[mid]>tar){
      return  binarysearch(arr, tar, left, mid-1);
    }

        return false;
    }
    public static void main (String[] args)
    {
        int[] array = {1,2,3,4,5,6,7,8,9,};
        int target = 7;
        boolean search = binarysearch(array, target);
        if (search) {
            System.out.println("Target " + target + " found in the array.");
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }
}
