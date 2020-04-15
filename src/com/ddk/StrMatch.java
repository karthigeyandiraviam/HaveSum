package com.ddk;

public class StrMatch {
    public static Boolean isMatch(String str1, String str2) {
        Boolean match = true;
        int s2Idx = 0 , s1Idx = 0;
        Character prevChar = null;
        while ( match ) {
            if ( s2Idx < str2.length() && str2.charAt(s2Idx) == '*' ) {
                // zero or more occurrences
                prevChar = ( s1Idx > 0 ) ? str1.charAt(s1Idx-1) : null;
                while ( s1Idx < str1.length() && prevChar != null && str1.charAt(s1Idx) == prevChar )
                    s1Idx++;
                if ( prevChar == null )
                    s1Idx++;
                s2Idx++;
            } else if ( s2Idx < str2.length() && str2.charAt(s2Idx) == '+' ) {
                // zero or more occurrences
                prevChar = ( s1Idx > 0 ) ? str1.charAt(s1Idx-1) : null;
                s1Idx++;
                while ( s1Idx < str1.length() && prevChar != null && str1.charAt(s1Idx) == prevChar )
                    s1Idx++;
                s2Idx++;
            } else if ( s2Idx < str2.length() && str2.charAt(s2Idx) == '?' ) {
                // zero or one occurrence
                prevChar = ( s1Idx > 0 ) ? str1.charAt(s1Idx-1) : null;
                if ( s1Idx < str1.length() && prevChar != null && str1.charAt(s1Idx) == prevChar ) {
                    s1Idx++;
                    s2Idx++;
                }
                if ( prevChar == null )
                    s1Idx++;
                s2Idx++;
            } else if ( s2Idx < str2.length() && str2.charAt(s2Idx) == '.' ) {
                // match any character at current position
                prevChar = str1.charAt(s1Idx);
                if ( prevChar == null ) {
                    match = false;
                    break;
                }
                s2Idx++;
                s1Idx++;
            } else if ( s1Idx < str1.length() && s2Idx < str2.length() && str1.charAt(s1Idx) == str2.charAt(s2Idx) ) {
                s2Idx++;
                s1Idx++;
            } else {
                match = false;
            }
            if ( s2Idx >= str2.length() )
                break;
            if ( s1Idx >= str1.length() )
                break;
        }
        return match;
    }

    public static void main(String[] args) {
        System.out.println( (isMatch("TThis ", "TThi?s ") == true) ); // Regex should match
        System.out.println( (isMatch("TThis ", "T+his ") == true) ); // Regex should match
        System.out.println( (isMatch("TThis ", "*Thi?s ") == true) ); // Regex should match
        System.out.println( (isMatch("TThis ", "T*his ") == true) ); // Regex should match
        System.out.println( (isMatch("TThis ", "T?This ") == true) ); // Regex should match
        System.out.println( (isMatch("This is random string to verify", "This is ran..m string to verify") == true) ); // Regex should match
        System.out.println(
                (isMatch("This is a random string to verify", "*hi?s is* a rando. string to *") == true)
        ); // Regex should match
    }
}
