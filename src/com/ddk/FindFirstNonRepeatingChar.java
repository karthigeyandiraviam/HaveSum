package com.ddk;

/*
 * String abca
 * return b
 * String abcdb
 * return a
 * String bbcada
 * return c
 */

import java.util.*;

public class FindFirstNonRepeatingChar {
    public static void main(String[] args) {
        Map<Character, List<Integer>> occur = new LinkedHashMap<>();
        String testString = "bbcada";
        for ( int i = 0 ; i < testString.length() ; i++ ) {
            if ( ! occur.containsKey(testString.charAt(i)) ) {
                List<Integer> index = new ArrayList<>();
                index.add(i);
                occur.put(testString.charAt(i), index);
            } else {
                occur.get(testString.charAt(i)).add(i);
            }
        }
        int index = -1;
        for ( Map.Entry<Character, List<Integer>> entry : occur.entrySet() ) {
            if ( entry.getValue().size() == 1 ) {
                index = entry.getValue().get(0);
                break;
            }
        }
        if ( index != -1 )
            System.out.println(testString.charAt(index));
        else
            System.out.println("Not Found");


        /*
        for ( Map.Entry<Character, Integer> entry : occur.entrySet() ) {
            if ( entry.getValue() == 1 ) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                break;
            }
        }
        */
    }
}


