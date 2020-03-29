package com.ddk;

import java.util.*;

public class LongestPloyndrome {
    static boolean isPolindrome(String s) {
        if ( s == null )
            return false;
        String rev = new StringBuilder(s).reverse().toString();
        if ( s.equals(rev) )
            return true;
        return false;
    }

    static String longestPalindrome(String s) {
        if ( s.length() == 1 )
            return s;

        Map<Integer, String> pols = new TreeMap<>();

        for ( int i = 0 ; i < s.length() ; i++ ) {
            for ( int j = i ; j < s.length() ; j++ ) {
                String sub = s.substring(i, j+1);
                if ( isPolindrome(sub) )
                    pols.put(sub.length(), sub);
            }
        }

        if ( pols.isEmpty() )
            return "";

        return ((TreeMap<Integer, String>) pols).lastEntry().getValue();
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }
}
