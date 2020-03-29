package com.ddk;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if(digits == null || digits.length() < 1)
            return result;
        String[] mapping = new String[] {
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };
        generateWord(digits, mapping, 0, "", result);
        return result;
    }

    private void generateWord(String digits,            // input given
                              String[] mapping,         // our internal mapping
                              int index,                // current index at "digits" string
                              String current,           // current combination of letters
                              List<String> result) {    // final result

        // Base case, also result case
        // When we reach end of digits (beyond)
        if(index == digits.length() && current.length() == digits.length()) {
            result.add(current);
            return;
        }

        for(int i = index; i < digits.length(); i++) {                  // recurse on all digits starting from current
            int digit = Character.getNumericValue(digits.charAt(i));

            for(int j = 0; j < mapping[digit].length(); j++) {          // Try all letters for this digit
                current += mapping[digit].charAt(j);                    // J th letter added
                generateWord(digits, mapping, i + 1, current, result);
                current = current.substring(0, current.length() - 1);   // J th letter removed
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> comb = letterCombinations.letterCombinations("23");
        comb.stream().forEach(a -> System.out.print(a + " "));
        System.out.println();
    }
}
