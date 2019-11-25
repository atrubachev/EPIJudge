package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsStringPermutableToPalindrome {
    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

    public static boolean canFormPalindrome(String s) {
        Map<Character, Integer> freq = computeCharacterFres(s);
        boolean existsOdd = false;
        for (Map.Entry<Character, Integer> chFreq : freq.entrySet()) {
            if (chFreq.getValue() % 2 != 0) {
                if (existsOdd) {
                    return false;
                }
                existsOdd = true;
            }
        }
        return true;
    }

    private static Map<Character, Integer> computeCharacterFres(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (Character ch : s.toCharArray()) {
            if (freq.containsKey(ch)) {
                freq.put(ch, freq.get(ch) + 1);
            } else {
                freq.put(ch, 1);
            }
        }
        return freq;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
