package com.ddk;

import java.util.Arrays;

public class ReverseString {
    static String reverseString(String str) {
        int start = 0;
        int end = str.length()-1;
        char[] chrs = str.toCharArray();
        while (start < end) {
            char c = chrs[start];
            chrs[start] = chrs[end];
            chrs[end] = c;
            start++;
            end--;
        }
        return new String(chrs);
    }

    public static void main(String[] args) {
        System.out.println(reverseString("madam"));
    }
}
