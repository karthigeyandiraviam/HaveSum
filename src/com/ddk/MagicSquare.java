package com.ddk;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MagicSquare {

    static void printMatrix(int[][] s) {
        int N = s.length;
        System.out.println("=============================");
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
                System.out.print(s[i][j]+" ");
            System.out.println();
        }
        System.out.println("=============================");
    }

    // Function to generate odd sized magic squares
    static void generateSquare(int n)
    {
        int[][] magicSquare = new int[n][n];

        // Initialize position for 1
        int i = 1;
        int j = 0;

        // One by one put all values in magic square
        for (int num=1; num <= n*n; )
        {
            if (i==-1 && j==n) //3rd condition
            {
                j = n-2;
                i = 0;
            }
            else
            {
                //1st condition helper if next number
                // goes to out of square's right side
                if (j == n)
                    j = 0;

                //1st condition helper if next number is
                // goes to out of square's upper side
                if (i < 0)
                    i=n-1;
            }

            //2nd condition
            if (magicSquare[i][j] != 0)
            {
                j -= 2;
                i++;
                continue;
            }
            else
                //set number
                magicSquare[i][j] = num++;

            //1st condition
            j++;  i--;
        }

        // print magic square
        System.out.println("The Magic Square for "+n+":");
        System.out.println("Sum of each row or column "+n*(n*n+1)/2+":");
        for(i=0; i<n; i++)
        {
            for(j=0; j<n; j++)
                System.out.print(magicSquare[i][j]+" ");
            System.out.println();
        }
    }

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        // print sum of rows
        int n = s.length;
        System.out.println("Matrix Length: " + n);
        int i = 0;
        int j = 0;
        boolean doesHave1 = false;
        int cost = 0;
        // Find position for 1
        for ( i = 0 ; i < n ; i++ ) {
            for ( j = 0; j < n; j++ ) {
                if ( s[i][j] == 1 ) {
                    System.out.println("Current position for 1 is: " + i + ", " + j);
                    doesHave1 = true;
                    break;
                }
            }
            if ( doesHave1 )
                break;
        }

        if ( doesHave1 ) {
            /*
            * *****
            * Rules
            * *****
            * 1. The position of next number is calculated by decrementing
            *    row number of previous number by 1, and incrementing the
            *    column number of previous number by 1. At any time, if the
            *    calculated row position becomes -1, it will wrap around to n-1.
            *    Similarly, if the calculated column position becomes n,
            *    it will wrap around to 0.
            * 2. If the magic square already contains a number at the
            *    calculated position, calculated column position will be
            *    decremented by 2, and calculated row position will be
            *    incremented by 1.
            * 3. If the calculated row position is -1 & calculated column
            *    position is n, the new position would be: (0, n-2).
            **/
            int[][] magicSquare = new int[n][n];
            try {
                for ( int num = 1 ; num <n*n ; ) {
                    magicSquare[i][j] = num++;
                    i++;
                    j--;
                    if ( j < 0 )
                        j = n-1;
                    if (i >= n)
                        i=0;
                    if ( magicSquare[i][j] != 0 ) {
                        i=+2;
                        j--;
                    }
                }
            } catch (Exception e) {
                printMatrix(magicSquare);
                e.printStackTrace();
            }
        }

        return cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
/*
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        printMatrix(s);
*/

        // int result = formingMagicSquare(s);
        generateSquare(3);

        // bufferedWriter.write("Cost: " + String.valueOf(result));
        // bufferedWriter.newLine();

        //bufferedWriter.close();

        //scanner.close();
    }
}
