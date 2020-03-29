package com.ddk;

public class MaxArea {
    public static int findArea(int[] height) {
        int area = -1;
        int iterations = 0;
        for ( int i = 0 ; i < height.length ; i++ ) {
            for ( int j = i+1 ; j < height.length ; j++ ) {
                area = Math.max(area,Math.min(height[i], height[j]) * (j-i));
                iterations++;
            }
        }
        System.out.println("Iterations: " + iterations);
        return area;
    }

    public static int findArea1(int[] height) {
        int area = -1;
        int i = 0, j = height.length-1;
        int iterations = 0;
        while ( i < j ) {
            area = Math.max(area,Math.min(height[i], height[j]) * (j-i));
            if ( height[i] < height[j] )
                i++;
            else
                j--;
            iterations++;
        }
        System.out.println("Iterations: " + iterations);
        return area;
    }

    public static void main(String[] args) {
        int[] height = {7, 7, 7, 7, 7, 7, 7};
        // int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        //int[] height = {3, 9, 3, 4, 7, 2, 12, 6};
        System.out.println("Area: " + findArea(height));
        System.out.println("Area: " + findArea1(height));
    }
}
