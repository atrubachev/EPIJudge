package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TwoSortedArraysMerge {

    private static void mergeTwoSortedArrays(List<Integer> A, int m, List<Integer> B, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A.get(i) > B.get(j)) {
                A.set(k--, A.get(i--));
            } else {
                A.set(k--, B.get(j--));
            }
        }
        while (j >= 0) {
            A.set(k--, B.get(j--));
        }
    }

    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    public static List<Integer> mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
        mergeTwoSortedArrays(A, m, B, n);
        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
