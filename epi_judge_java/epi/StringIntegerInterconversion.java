package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.HashMap;
import java.util.Map;

public class StringIntegerInterconversion {
    public static String intToString(int x) {
        if (x == 0) {
            return "0";
        }

        boolean negative = false;
        if (x < 0) {
            negative = true;
        }

        StringBuilder sBuilder = new StringBuilder();

        int carry = x;
        while (carry != 0) {
            sBuilder.append(Math.abs(carry % 10));
            carry /= 10;
        }

        if (negative) {
            sBuilder.append("-");
        }

        return sBuilder.reverse().toString();
    }

    public static int stringToInt(String s) {
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
        }

        Map<Character, Integer> digits = new HashMap<>();
        digits.put('0', 0);
        digits.put('1', 1);
        digits.put('2', 2);
        digits.put('3', 3);
        digits.put('4', 4);
        digits.put('5', 5);
        digits.put('6', 6);
        digits.put('7', 7);
        digits.put('8', 8);
        digits.put('9', 9);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (digits.containsKey(s.charAt(i))) {
                result *= 10;
                result += digits.get(s.charAt(i));
            }
        }

        if (negative) {
            result *= -1;
        }
        return result;
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (!intToString(x).equals(s)) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
