package com.ddk;

public class BinaryOps {
    static int bin(Integer N) {
        StringBuilder sb = new StringBuilder();
        boolean isGapStarted = false;
        int zeroCount = 0;
        int longZeroCount = 0;

        while ( N > 0 ) {
            int binNum = (N%2);
            if ( binNum == 1 ) {
                if ( zeroCount > 0 ) {
                    if ( longZeroCount < zeroCount )
                        longZeroCount = zeroCount;
                }
                isGapStarted = true;
                zeroCount = 0;
            } else {
                if ( isGapStarted )
                    zeroCount++;
            }
            sb.append( (binNum == 1) ? 1 : 0 );
            N /= 2;
        }
        System.out.println(sb.reverse().toString());
        return longZeroCount;
    }
    public static void main(String[] args) {
        System.out.println(bin(32));
    }
}
