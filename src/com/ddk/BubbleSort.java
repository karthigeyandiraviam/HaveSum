package com.ddk;

import java.util.Arrays;

public class BubbleSort {
    static void bubbleSort1(int[] array) {
        boolean isSwapped;
        do {
            isSwapped = false;
            for (int i = 0; i < array.length-1; i++) {
                if ( array[i] > array[i+1] ) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    isSwapped = true;
                    continue;
                }
            }
        } while ( isSwapped );
    }

    static void bubbleSort2(int[] array) {
        boolean isSwapped;
        for ( int i = 0 ; i < array.length-1; i++ ) {
            isSwapped = false;
            for ( int j = 0 ; j < array.length-i-1 ; j++ ) {
                if ( array[j] > array[j+1] ) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSwapped = true;
                }
            }
            if ( ! isSwapped )
                break;
        }
    }

    public static void main(String[] args) {
        int[] A = {5, 1, 4, 2, 8};

        System.out.println("Printing A");
        Arrays.stream(A).forEach(e -> System.out.print(e + " "));
        System.out.println("\n======================");

        int[] B = Arrays.copyOf(A, A.length);
        bubbleSort1(B);
        System.out.println("Printing B after bubbleSort1");
        Arrays.stream(B).forEach(e -> System.out.print(e + " "));
        System.out.println("\n======================");

        B = Arrays.copyOf(A, A.length);
        bubbleSort2(B);
        System.out.println("Printing B after bubbleSort2");
        Arrays.stream(B).forEach(e -> System.out.print(e + " "));
        System.out.println("\n======================");
    }
}
