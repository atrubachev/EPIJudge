package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

    public static String encoding(String s) {
        StringBuilder result = new StringBuilder();
        int slow = 0;
        int fast = 0;

        while (fast < s.length()) {
            while (fast < s.length() && s.charAt(slow) == s.charAt(fast)) {
                fast++;
            }
            result.append(fast - slow);
            result.append(s.charAt(slow));
            slow = fast;
        }

        return result.toString();
    }

    public static String decoding(String s) {
        StringBuilder result = new StringBuilder();
        int slow = 0;
        int fast = 0;

        while (fast < s.length()) {
            while (fast < s.length() && Character.isDigit(s.charAt(fast))) {
                fast++;
            }
            int count = Integer.parseInt(s.substring(slow, fast));
            result.append(String.valueOf(s.charAt(fast)).repeat(Math.max(0, count)));
            fast++;
            slow = fast;
        }

        return result.toString();
    }

    @EpiTest(testDataFile = "run_length_compression.tsv")
    public static void rleTester(String encoded, String decoded)
            throws TestFailure {
        if (!decoding(encoded).equals(decoded)) {
            throw new TestFailure("Decoding failed");
        }
        if (!encoding(decoded).equals(encoded)) {
            throw new TestFailure("Encoding failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
