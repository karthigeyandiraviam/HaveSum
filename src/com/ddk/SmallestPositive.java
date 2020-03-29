package com.ddk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SmallestPositive {
    static int solution(int[] A) {
        // write your code in Java SE 8
        int smallest = 1;
        if ( A.length > 0 ) {
            List<Integer> sorted = Arrays.stream(A).boxed().sorted().collect(Collectors.toList());
            for (int a : sorted) {
                if (a > 0 && smallest >= a)
                    smallest = a + 1;
            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        int[] A = {-2, -1, 1, 2, 3};
        System.out.println(solution(A));
    }
}
