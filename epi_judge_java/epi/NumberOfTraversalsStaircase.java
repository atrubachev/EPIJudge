package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsStaircase {
    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")

    public static int numberOfWaysToTop(int top, int maximumStep) {
        if (top < 2) {
            return 1;
        }
        int[] cache = new int[top + 1];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i <= top; i++) {
            for (int j = 1; j <= maximumStep; j++) {
                if (i - j < 0) {
                    break;
                }
                cache[i] += cache[i - j];
            }
        }
        return cache[top];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
