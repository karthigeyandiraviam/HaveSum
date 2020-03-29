package com.ddk;

public class FrogJmp {

    static int solution(int X, int Y, int D) {
        int diff = Y - X;
        if ( diff < 0 )
            return 0;
        double val = diff / D;
        int mod = diff % D;
        if ( mod > 0 )
            val++;
        return (int)val;
    }

    public static void main(String[] args) {
        int[] A = new int[0];
        System.out.println(solution(100, 100, 30));
    }
}
