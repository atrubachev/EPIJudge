package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

    private static void reverseWordsNaive(char[] input) {
        int size = input.length;
        char[] output = new char[size];
        int begin = 0;

        for (int end = 0; end < size; end++) {
            if (input[end] == ' ') {
                while (begin <= end) {
                    output[size - begin - 1] = input[begin];
                    begin++;
                }
            }
        }

        System.arraycopy(output, 0, input, 0, size);
    }

    private static void reverseWords(char[] input) {
        int n = input.length;

        reverse(input, 0, n - 1);

        int start = 0;
        int end = 0;
        while (end < n) {
            while (end < n && input[end] != ' ') {
                end++;
            }
            reverse(input, start, end);
            end++;
            start = end;
        }
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s)
            throws Exception {
        char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
