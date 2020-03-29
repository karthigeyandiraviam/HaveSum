package com.ddk;

public class RotateWords {
    public static String rotateWords(String sentence, int numRotation) {
        String[] splits = sentence.split(" |\t");
        if ( numRotation >= splits.length )
            numRotation -= splits.length;
        System.out.println("Num Rotation: " + numRotation);
        for ( int i = 0 ; i < numRotation ; i++ ) {
            String lastWord = splits[splits.length-1];
            for ( int j = splits.length-1 ; j > 0 ; j-- ) {
                 splits[j] = splits[j-1];
            }
            splits[0] = lastWord;
        }
        return String.join(" ", splits);
    }

    public static void main(String[] args) {
        System.out.println(rotateWords("This is a sentence to rotate", 8));
    }
}
