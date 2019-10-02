package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;

public class RomanToInteger {
    @EpiTest(testDataFile = "roman_to_integer.tsv")

    public static int romanToInteger(String s) {
        Map<Character, Integer> romanToInt = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

        int result = 0;
        int prev = 0;

        if (s.length() > 0) {
            prev = romanToInt.get(s.charAt(s.length() - 1));
            result += prev;
        }

        for (int i = s.length() - 2; i >= 0; i--) {
            int curr = romanToInt.get(s.charAt(i));
            if (curr < prev) {
                result -= curr;
            } else {
                result += curr;
            }
            prev = curr;
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
