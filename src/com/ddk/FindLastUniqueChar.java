package com.ddk;

import java.util.*;

/*
Given a String
Find the last character that's not repeated more than once
 */
public class FindLastUniqueChar {
    static Character findLastUniqueChar(String s) {
        LinkedHashMap<Character, Integer> oc = new LinkedHashMap<>();
        for ( int i = 0 ; i < s.length() ; i++ ) {
            oc.put(s.charAt(i), (oc.containsKey(s.charAt(i))?oc.get(s.charAt(i)) + 1 : 1));
        }
        List<Character> keys = new ArrayList<>(oc.keySet());
        Collections.reverse(keys);
        for ( Character c : keys ) {
            if ( oc.get(c) == 1 )
                return c;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(findLastUniqueChar("AGCDEFEDCBFA"));
    }
}
