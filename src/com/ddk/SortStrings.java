package com.ddk;

/*
 * write a function to arrange the string array alphabectically, like dictionary
 * condition: cannot use the built in sort function
 * nonalphabetic strings will be removed.
 *
 * Arry = {"vcd", "A", "ab", "a", "23a", "@#$%bcd", "CDF"}
 * Outarr = {"a", "A", "ab", "CDF", "vcd"}
 *
 * str.matches("^[a-zA-Z]*$")


 */


/*
Write test cases for your function:
- All alpha string in reverse order
- All alpha string in sorted order
- All alpha string in unsorted order
- All alpha string - same string in the array
- One alpha string
- Empty Array
- Null - should throw an exception
- Mix of Alpha and Non-Alpha string
- All Non-Alpha strings - empty result
- All empty string in the array
- Mix of alpha string and empty strings
- Number of elements is large in the array
- each String going to be Long ones

*/

import java.util.ArrayList;
import java.util.List;

public class SortStrings {
    public static int compareTo(String s1, String s2) {
        if ( s1 == null )
            return 1;
        if ( s2 == null )
            return -1;
        if ( s1.length() < s2.length() )
            return -1;
        else if ( s1.length() > s2.length() )
            return 1;
        else { // length are equal
            for ( int i = 0 ; i < s1.length() ; i++ ) {
                if ( s1.charAt(i) < s2.charAt(i) ) {
                    if ( s1.charAt(i)+32 == s2.charAt(i) )
                        return 1;
                    else
                        return -1;
                } else if ( s1.charAt(i) > s2.charAt(i) ) {
                    if ( s2.charAt(i)+32 == s1.charAt(i) )
                        return -1;
                    else
                        return 1;
                }
            }
        }
        return 0;
    }

    public static void merge(String[] strArray, String[] left, String[] right, int l, int r) {
        int i = 0, j = 0, k = 0;
        while ( i < l && j < r ) {
            if ( compareTo(left[i], right[j]) < 0 )
                strArray[k++] = left[i++];
            else
                strArray[k++] = right[j++];
        }
        while ( i < l )
            strArray[k++] = left[i++];
        while ( j < r )
            strArray[k++] = right[j++];
    }

    public static void sortStr(String[] strArray, int len) {
        if ( len < 2 )
            return;
        int mid = len / 2;
        String[] left = new String[mid];
        String[] right = new String[len - mid];
        for ( int i = 0 ; i < mid ; i++ )
            left[i] = strArray[i];

        for ( int i = mid ; i < len; i++ )
            right[i - mid] = strArray[i];

        sortStr(left, mid);
        sortStr(right, len - mid);
        merge(strArray, left, right, mid, len - mid);
    }

    public static boolean isAlphaStr(String str) {
        return str.matches("^[a-zA-Z]+$");
    }

    public static void main(String[] args) {
        String[] strArray = {"vcd", "A", "ab", "a", "23a", "@#$%bcd", "CDF"};
        List<String> alphaStrList = new ArrayList<>();
        for ( int i = 0 ; i < strArray.length ; i++ ) {
            if (isAlphaStr(strArray[i]))
                alphaStrList.add(strArray[i]);
        }
        strArray = alphaStrList.stream().toArray(String[]::new);
        sortStr(strArray, strArray.length);
        for ( int i = 0 ; i < strArray.length ; i++ )
            System.out.println(strArray[i]);
    }
}

