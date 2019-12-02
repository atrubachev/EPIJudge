package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestContainedInterval {
    @EpiTest(testDataFile = "longest_contained_interval.tsv")

    public static int longestContainedRange(List<Integer> A) {
        Set<Integer> nums = new HashSet<>(A);
        int maxInterval = 0;

        while (!nums.isEmpty()) {
            int n = nums.iterator().next();
            nums.remove(n);

            int leftN = n - 1;
            while (nums.contains(leftN)) {
                nums.remove(leftN);
                leftN--;
            }

            int rightN = n + 1;
            while (nums.contains(rightN)) {
                nums.remove(rightN);
                rightN++;
            }

            maxInterval = Math.max(maxInterval, rightN - leftN - 1);
        }

        return maxInterval;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
