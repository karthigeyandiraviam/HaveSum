package com.ddk;

import java.util.Arrays;

public class ZigZagStr {
    public static String convert(String s, int numRows) {
        if ( numRows <= 1 )
            return s;
        String[] strArray = new String[numRows];
        Arrays.fill(strArray,"");
        int idx = 0;
        boolean isRev = false;
        for ( int i = 0 ; i < s.length() ; i++ ) {
            strArray[idx] = strArray[idx] + s.charAt(i);
            if ( (idx+1) == numRows )
                isRev = true;
            if ( isRev )
                idx--;
            else
                idx++;
            if ( idx < 0 ) {
                idx = 1;
                isRev = false;
            }
        }
        return String.join("", strArray);
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 1));
    }
}
