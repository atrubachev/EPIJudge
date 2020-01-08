package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ThreeSum {
    @EpiTest(testDataFile = "three_sum.tsv")

    public static boolean hasThreeSum(List<Integer> A, int t) {
        Collections.sort(A);
        for (Integer num1 : A) {
            for (Integer num2 : A) {
                if (Collections.binarySearch(A, t - num1 - num2) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
