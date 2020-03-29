package com.ddk;

public class LongestSubstr {
    public int lengthOfLongestSubstring(String s) {
        int longCharSeq = 0;
        String a = "";
        for ( int i = 0 ; i < s.length() ; i++ ) {
            if ( a.contains(s.charAt(i) + "") ) {
                if ( longCharSeq < a.length() )
                    longCharSeq = a.length();
                i = s.substring(0,i).lastIndexOf(s.charAt(i))+1;
                a="";
            }
            a+=s.charAt(i);
        }

        if ( longCharSeq < a.length() )
            return a.length();

        if ( longCharSeq == 0 )
            return s.length();

        return longCharSeq;
    }

    public static void main(String[] args) {
        LongestSubstr lngSubStr = new LongestSubstr();
        System.out.println(lngSubStr.lengthOfLongestSubstring("ckilbkd"));
    }
}
