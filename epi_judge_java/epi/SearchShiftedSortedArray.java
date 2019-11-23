package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {
    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

    public static int searchSmallest(List<Integer> A) {
        int l = 0;
        int h = A.size() - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (A.get(m) > A.get(h)) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
