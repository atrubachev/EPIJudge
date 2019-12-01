package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubarrayWithDistinctValues {
    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")

    public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
        Set<Integer> seen = new HashSet<>();
        int count = 0;
        int max = 0;

        for (int i = 0; i < A.size(); i++) {
            while (!seen.isEmpty() && seen.contains(A.get(i))) {
                seen.remove(A.get(i - count));
                count--;
            }
            seen.add(A.get(i));
            count++;
            max = Math.max(max, count);
        }

        return max;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
