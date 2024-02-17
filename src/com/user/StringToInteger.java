package com.user;

public class StringToInteger {

public static int convertToNumber(String Snumber) {
    return convertToIntegerHelper(Snumber, 0);
}
    public static int convertToIntegerHelper(String Snumber, int index){
        if (index == Snumber.length()) {
            return 0;
        }
        int digit = Character.getNumericValue(Snumber.charAt(index));
        return digit + 10* convertToIntegerHelper(Snumber, index+1);
    }




    public static void main(String[] args){
        String Snumber = "13451";
        int result = convertToNumber(Snumber);
        System.out.print(result);

    }
}
