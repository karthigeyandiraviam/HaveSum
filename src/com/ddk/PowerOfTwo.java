package com.ddk;

import java.util.*;

/*
 * Write a fucntion that returns true if a given number is poer of 2, returns false if it is not.
 * 
 */

public class PowerOfTwo {
  /*
  2^2 = 4
  2^3 = 8
  */
  static Random r = new Random();

  public static boolean findIfPowOfTwo(int a) {
    if ( a < 2 )
      return false;
    while ( a > 2  ) {
      if ( (a % 2) != 0 )
        return false;
      a /= 2;
    }
    return true;
  }
  
  public static int returnRand(int max) {
    // return r.nextInt(max);
    if ( r.nextBoolean() ) 
      return max - 1;
    else if ( r.nextBoolean() ) 
      return max - 2;
    else
      return max -3;
    
    // return max - 1;
  }
  // 2, 64, 10, 1023
  public static void main(String[] args) {
    System.out.println(findIfPowOfTwo(2));
    System.out.println(findIfPowOfTwo(64));
    System.out.println(findIfPowOfTwo(10));
    System.out.println(findIfPowOfTwo(1023));
    System.out.println(findIfPowOfTwo(16));
    /*
    HashMap<Integer, Integer> count = new HashMap<>();
    int iteration = 100;
    int i = 0;
    boolean test = true;
    while ( i < iteration ) {
      int r = returnRand(10);
      System.out.println(r);
      count.put(r, count.containsKey(r) ? count.get(r)+1 : 1);
      if ( count.get(r) > iteration/4 ) {
        // Throw exception
        System.out.println(r + " is returned more than 50%\n");
        test = false;
        break;
      }
      i++;
    }
    System.out.println(test);
    */
      
    //System.out.println(returnRand(-1));
    //System.out.println(returnRand(Integer.MAX_VALUE) < Integer.MAX_VALUE);
  }
}
