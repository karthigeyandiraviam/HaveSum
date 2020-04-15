package com.ddk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateParathensis {

/*
    public static int factorial(int n) {
        if ( n == 0 )
            return 1;
        else
            return n * factorial(n-1);
    }
*/

    public static void _generateParathensis(String[] str, int pos, int n, int open, int close) {
        if ( close != n ) {
            if ( open > close ) {
                str[pos] = ")";
                _generateParathensis(str, pos+1, n, open, close+1);
            }
            if ( open < n ) {
                str[pos] = "(";
                _generateParathensis(str, pos+1, n, open+1, close);
            }
        }
    }
    public static List<String> generateParathensis(int n) {
        String[] str = new String[2*n];
        _generateParathensis(str, 0, n, 0, 0);
        return Arrays.asList(str);
    }

    public static void main(String[] args) {
        List<String> combos = generateParathensis(6);
        combos.stream().forEach(a -> System.out.print(a + " "));
        System.out.println();
    }
}
