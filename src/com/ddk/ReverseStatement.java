package com.ddk;

import java.util.Arrays;

public class ReverseStatement {
    public static void main(String[] args) {
        String str = "This is Netskope Netskope is This sihT si epoksteN";
        String[] split = str.split(" ");
        for ( int i = 0 ; i < split.length/2 ; i++ ) {
            String temp = split[split.length-(i+1)];
            split[split.length-(i+1)] = split[i];
            split[i] = temp;
        }
        System.out.println(Arrays.toString(split));
        for ( int i = 0 ; i < split.length ; i++ ) {
            char[] charSeq = split[i].toCharArray();
            for ( int j = 0 ; j < split[i].length()/2 ; j++ ) {
                char temp = charSeq[split[i].length()-(j+1)];
                charSeq[split[i].length()-(j+1)] = charSeq[j];
                charSeq[j] = temp;
            }
            split[i] = String.valueOf(charSeq);
        }
        System.out.println(Arrays.toString(split));
    }
}
