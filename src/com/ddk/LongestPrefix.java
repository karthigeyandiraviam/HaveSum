package com.ddk;

public class LongestPrefix {
    public static String longestCommonPrefix(String[] strs) {
        String prefix = "\"";
        boolean breakOuterLoop = false;
        for ( int index = 0 ; ! breakOuterLoop ; index++ ) {
            char c = '\0';
            int numCharFound = 0;
            for ( int i = 0 ; i < strs.length ; i++ ) {
                if ( strs[i].length() > 0 && index < strs[i].length() ) {
                    if ( c == '\0' )
                        c = strs[i].charAt(index);
                    else if ( c != strs[i].charAt(index) ){
                        c = '\0';
                        breakOuterLoop = true;
                        break;
                    }
                } else {
                    breakOuterLoop = true;
                    break;
                }
                numCharFound++;
            }
            if ( c != '\0' && numCharFound == strs.length )
                prefix += c;
            numCharFound = 0;
        }
        return prefix + "\"";
    }

    public static String longestCommonPrefix1(String[] strs) {
        String commonPrefix = "";

        if(strs.length < 1) return commonPrefix; // if strs is empty, return ""

        for(int i=1;i<=strs[0].length();i++){
            String prefix = strs[0].substring(0, i); // make substring of str[0], from 0 to i

            for(int j=1;j<strs.length;j++){
                if(!strs[j].startsWith(prefix)) { // if not prefix of this str
                    return commonPrefix;
                }
            }

            commonPrefix = prefix; // all matches prefix then update commonPrefix
        }

        return commonPrefix;
    }

    public static void testPerf(String[] strs) {
        long start = System.nanoTime();
        String prefix = longestCommonPrefix(new String[]{"dog","racecar","car"});
        System.out.println(prefix + " ");
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"flower","flower","flower"}));
        System.out.println(longestCommonPrefix(new String[]{"f","f","f"}));
        System.out.println(longestCommonPrefix(new String[]{"f","",""}));
        System.out.println(longestCommonPrefix(new String[]{"\n","\n","\n"}));

        System.out.println(longestCommonPrefix1(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix1(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix1(new String[]{"flower","flower","flower"}));
        System.out.println(longestCommonPrefix1(new String[]{"f","f","f"}));
        System.out.println(longestCommonPrefix1(new String[]{"f","",""}));
        System.out.println(longestCommonPrefix1(new String[]{"\n","\n","\n"}));
    }
}
