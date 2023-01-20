package krekks.easyparkour.misc;

import java.util.Scanner;

public class KrekksString {
    //keeps digits
    public static int getIntFromString(String input){
        char[] charArray = input.toCharArray();            //The array
        int sl = input.toCharArray().length;               //The amount of characters in the chararray
        StringBuilder result = new StringBuilder();         //String magic
        for (int i = 0; i < sl; i++){
            if (Character.isDigit(charArray[i])){           //magic
                result.append(charArray[i]);
            }
        }
        return Integer.parseInt(result.toString());
    }
    //TODO: test this function
    public static double getDoubleFromString(String input){
        Scanner st = new Scanner(input);
        while (!st.hasNextDouble())
        {
            st.next();
        }
        return st.nextDouble();
    }

}
