package com.ddk;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountSort {

    void printArray(char arr[]) {
        System.out.print("character array is ");
        IntStream.range(0, arr.length).mapToObj(a -> arr[a]).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    void printArray(int arr[]) {
        System.out.print("integer array is ");
        Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    void countSort(char arr[])
    {
        int n = arr.length;

        // The output character array that will have sorted arr
        char output[] = new char[n];

        // Create a count array to store count of inidividul
        // characters and initialize count array as 0
        int count[] = new int[256];

        // store count of each character
        for (int i=0; i<n; ++i)
            ++count[arr[i]];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i=1; i<=255; ++i)
            count[i] += count[i - 1];

        // Build the output character array
        // To make it stable we are operating in reverse order.
        for (int i = n-1; i>=0; i--)
        {
            output[count[arr[i]]-1] = arr[i];
            --count[arr[i]];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i<n; ++i)
            arr[i] = output[i];
    }

    // Driver method
    public static void main(String args[])
    {
        CountSort ob = new CountSort();
        char arr[] = {'g', 'e', 'e', 'k', 's', 'f', 'o',
                'r', 'g', 'e', 'e', 'k', 's'
        };

        // System.out.print("Unsorted character array is ");
        IntStream.range(0, arr.length).mapToObj(a -> arr[a]).forEach(a -> System.out.print(a + " "));
        System.out.println();

        ob.countSort(arr);

        // System.out.print("Sorted character array is ");
        IntStream.range(0, arr.length).mapToObj(a -> arr[a]).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }
}
