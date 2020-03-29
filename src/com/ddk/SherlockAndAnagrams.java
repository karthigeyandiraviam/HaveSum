package com.ddk;

import java.io.IOException;
import java.util.*;

public class SherlockAndAnagrams {
    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        int total = 0;
        for ( int i = 0 ; i < s.length() ; i++ ) {
            for ( int j = i+1 ; j <= s.length() ; j++ ) {
                // get substring and sort it!
                String sub = s.substring(i,j);

                char tempArray[] = sub.toCharArray();
                Arrays.sort(tempArray);
                sub = new String(tempArray);

                if(map.containsKey(sub)){
                    total+=map.get(sub);
                    map.put(sub, map.get(sub)+1);
                } else {
                    // add to map if not seen
                    map.put(sub, 1);
                }
            }
        }

        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            System.out.println(String.valueOf(result));
        }

        scanner.close();
    }
}
