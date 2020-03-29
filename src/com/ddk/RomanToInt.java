package com.ddk;

import java.util.HashMap;
import java.util.Map;

/*
Your previous Python 3 content is preserved below:

'''
M = 1000
D = 500
C = 100
L = 50
X = 10
V = 5
I = 1

MMXVIII = 2018
1000 + 1000 + 10 + 5 + 1 + 1 + 1 = 2018

MCMXLIX = 1949
1000 + (-100) + 1000 + (-10) + 50 + (-1) + 10 = 1949

IX = 9
X = 10
XI =11
'''
 */

public class RomanToInt {
    public static int romanToInt(String romString) {
        Map<Character, Integer> rToI = new HashMap<Character, Integer>() {{
            put('M', 1000);
            put('D', 500);
            put('C', 100);
            put('L', 50);
            put('X', 10);
            put('V', 5);
            put('I', 1);
        }};
        int ret = romString.charAt(0);
    /*
    for ( int i = 1 ; i < romString.length()  ; i++ ) {
      if ( rToI.get(romString.charAt(i)) < rToI.get(romString.charAt(i-1)) )
        ret -= rToI.get(romString.charAt(i));
      else
        ret += rToI.get(romString.charAt(i));
      System.out.println(ret);
    }
    System.out.println(ret);
    */

        // Step 2
        ret = 0;
        for ( int i = 0 ; i < romString.length()-1 ; i++ ) {
            if ( rToI.get(romString.charAt(i)) < rToI.get(romString.charAt(i+1)) )
                ret -= rToI.get(romString.charAt(i));
            else
                ret += rToI.get(romString.charAt(i));
            System.out.println(ret + " " + romString.charAt(i));
        }
        ret += rToI.get(romString.charAt(romString.length()-1));
        return ret;
    }
    public static void main(String[] args) {
        System.out.println( (romanToInt("MCMXLIX") == 1949) );
        System.out.println( (romanToInt("MMXVIII") == 2018) );
    }
}
