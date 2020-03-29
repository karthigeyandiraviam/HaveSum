package com.ddk;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

public class FindOddNumber {
    static int findOddNumber(int[] A) {
        Map<Integer, Integer> numOccurence = new Hashtable<>();
        if ( A.length > 0 && A.length % 2 == 1 ) {
            for ( int a : A ) {
                int count = numOccurence.containsKey(a) ? numOccurence.get(a) + 1 : 1;
                if ( count == 1 )
                    numOccurence.put(a, count);
                else {
                    if ( numOccurence.containsKey(a) )
                        numOccurence.remove(a);
                }
            }
        }
        return (
                numOccurence.isEmpty() ? 0 :
                numOccurence.keySet().iterator().next()
        );
    }
    public static void main(String[] args) {
        int[] A = new int[0];
        System.out.println(findOddNumber(A));
    }
}
