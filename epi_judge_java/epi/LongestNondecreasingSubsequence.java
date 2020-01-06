package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestNondecreasingSubsequence {
    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")

    public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
        List<Integer> longestByEnd = new ArrayList<>(A.size());
        for (int i = 0; i < A.size(); i++) {
            longestByEnd.add(1);
        }
        for (int i = 1; i < A.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (A.get(i).compareTo(A.get(j)) >= 0) {
                    longestByEnd.set(i, Math.max(longestByEnd.get(i), longestByEnd.get(j) + 1));
                }
            }
        }
        return Collections.max(longestByEnd);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
