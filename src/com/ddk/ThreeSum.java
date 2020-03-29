package com.ddk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {
    class Triplet {
        List<Integer> tpls;
        public Triplet(int x, int y, int z) {
            if ( tpls == null )
                tpls = new ArrayList<>();
            tpls.add(x);
            tpls.add(y);
            tpls.add(z);
            Collections.sort(tpls);
        }

        public List<Integer> getList() {
            return tpls;
        }

        @Override
        public boolean equals(Object o) {
            Triplet t = (Triplet) o;
            return t.tpls.equals(tpls);
        }

        @Override
        public int hashCode() {
            return tpls.hashCode();
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        if ( nums.length > 2 ) {
            HashSet<Triplet> trHashSet = new HashSet<>();
            for ( int i = 0 ; i < nums.length ; i++ ) {
                for ( int j = 0 ; j < nums.length-1 ; j++ ) {
                    if ( i != j && i != j+1) {
                        if ( (nums[i]+nums[j]+nums[j+1]) == 0 ) {
                            Triplet t = new Triplet(nums[i], nums[j], nums[j+1]);
                            trHashSet.add(t);
                        }
                    }
                }
            }
            for ( Triplet t : trHashSet ) {
                triplets.add(t.getList());
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> result = threeSum.threeSum(new int[]{3, -2, 1, 0});
        for ( List<Integer> r : result ) {
            r.stream().forEach(a -> System.out.print(a + ", "));
            System.out.println();
        }

        result = threeSum.threeSum(new int[]{-1,0,1,2,-1,-4});
        for ( List<Integer> r : result ) {
            r.stream().forEach(a -> System.out.print(a + ", "));
            System.out.println();
        }
    }
}
