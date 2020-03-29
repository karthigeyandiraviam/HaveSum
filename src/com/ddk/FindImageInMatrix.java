package com.ddk;

/*

Imagine we have an image. We'll represent this image as a simple 2D array where every pixel is a 1 or a 0.

The image you get is known to have potentially many distinct rectangles of 0s on a background of 1's. Write a function that takes in the image and returns the coordinates of all the 0 rectangles -- top-left and bottom-right; or top-left, width and height.

image1 = [
  [0, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [0, 1, 1, 0, 0, 0, 1],
  [1, 0, 1, 0, 0, 0, 1],
  [1, 0, 1, 1, 1, 1, 1],
  [1, 0, 1, 0, 0, 1, 1],
  [1, 1, 1, 0, 0, 1, 1],
  [1, 1, 1, 1, 1, 1, 0],
]

Sample output variations (only one is necessary):

findRectangles(image1) =>
  // (using top-left-row-column and bottom-right):
  [
    [[0,0],[0,0]],
    [[2,0],[2,0]],
    [[2,3],[3,5]],
    [[3,1],[5,1]],
    [[5,3],[6,4]],
    [[7,6],[7,6]],
  ]
  // (using top-left-x-y and width/height):
  [
    [[0,0],[1,1]],
    [[0,2],[1,1]],
    [[3,2],[3,2]],
    [[1,3],[1,3]],
    [[3,5],[2,2]],
    [[6,7],[1,1]],
  ]

Other test cases:

image2 = [
  [0],
]

findRectangles(image2) =>
  // (using top-left-row-column and bottom-right):
  [
    [[0,0],[0,0]],
  ]

  // (using top-left-x-y and width/height):
  [
    [[0,0],[1,1]],
  ]

image3 = [
  [1],
]

findRectangles(image3) => []

image4 = [
  [1, 1, 1, 1, 1],
  [1, 0, 0, 0, 1],
  [1, 0, 0, 0, 1],
  [1, 0, 0, 0, 1],
  [1, 1, 1, 1, 1],
]

findRectangles(image4) =>
  // (using top-left-row-column and bottom-right or top-left-x-y and width/height):
  [
    [[1,1],[3,3]],
  ]

n: number of rows in the input image
m: number of columns in the input image



*/

import java.io.*;
import java.util.*;

public class FindImageInMatrix {
  /*
  public static int[] findRectangle(int[][] image) {
    int[] result = new int[4];
    boolean found = false;
    for ( int i = 0 ; i < image.length ; i++ ) {
      for ( int j = 0 ; j < image[i].length ; j++ ) {
        if ( image[i][j] == 0 ) {
          found=true;
          result[0] = i;
          result[1] = j;
          //System.out.println("i=" + i + ", j=" + j);
          break;
        }
      }
      if ( found )
        break;
    }
    int i = result[0];
    int j = result[1];
    while ( j < image[i].length && image[i][j] != 1 ) {
      result[2]++;
      j++;
    }
    j = result[1];
    while ( i < image.length && image[i][j] != 1 ) {
      result[3]++;
      i++;
    }
    return result;
  }
  */


    public static int[][] findRectangles(int[][] image) {
        int[][] results = new int[image.length][4];
        int[][] visited = new int[image.length][image[0].length];

        int resCount = 0;
        for ( int i = 0 ; i < image.length ; i++ ) {
            for ( int j = 0 ; j < image[i].length ; j++ ) {
                if ( image[i][j] == 0 && visited[i][j] != 1) {
                    results[resCount][0] = i;
                    results[resCount][1] = i;
                    int k = i;
                    int l = j;
                    while ( l < image[k].length && image[k][l] != 1 ) {
                        visited[k][l] = 1;
                        results[resCount][2]++;
                        l++;
                    }
                    l = j;
                    while ( k < image.length && image[k][l] != 1 ) {
                        visited[k][l] = 1;
                        results[resCount][3]++;
                        k++;
                    }
                    resCount++;
                }
            }
        }
        return results;
    }

    public static void printResults(int[][] results) {
        for ( int i = 0 ; i < results.length ; i++ ) {
            System.out.println(results[i][0] + "," + results[i][1] + ", width: " + results[i][2] + ", height: " + results[i][3]);
        }
    }
    public static void main(String[] argv) {
        int[][] image1 = {
                {0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 0},
        };

        int[][] image2 = {
                {0},
        };

        int[][] image3 = {
                {1},
        };

        int[][] image4 = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
        };


//     int[] result = findRectangle(image1);
//     Arrays.stream(result).forEach( a -> System.out.print(a + " "));
//     System.out.println();

//     result = findRectangle(image2);
//     Arrays.stream(result).forEach( a -> System.out.print(a + " "));
//     System.out.println();

//     result = findRectangle(image3);
//     Arrays.stream(result).forEach( a -> System.out.print(a + " "));
//     System.out.println();

//     result = findRectangle(image4);
//     Arrays.stream(result).forEach( a -> System.out.print(a + " "));
//     System.out.println();

//     result = findRectangle(image5);
//     Arrays.stream(result).forEach( a -> System.out.print(a + " "));
//     System.out.println();

        System.out.println("Image1");
        int[][] result = findRectangles(image1);
        printResults(result);

        System.out.println("Image2");
        result = findRectangles(image2);
        printResults(result);

        System.out.println("Image3");
        result = findRectangles(image3);
        printResults(result);

        System.out.println("Image4");
        result = findRectangles(image4);
        printResults(result);
    }
}