package com.ddk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SplitArrayToBuckets {
    static void bucket(int arrayLength, int numBucket) throws Exception {
        if ( numBucket <= 0 )
            throw new Exception("Bucket should be > 0");
        int[] A = new int[arrayLength];
        for ( int i = 0 ; i < arrayLength ; i++ )
            A[i] = i+1;
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        for ( int a : A ) {
            if ( i++ % numBucket == 0 )
                result.add(new ArrayList<>());
            result.get(result.size()-1).add(a);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        try {
            bucket(10, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
