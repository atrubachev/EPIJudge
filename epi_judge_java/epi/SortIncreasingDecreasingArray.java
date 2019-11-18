package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortIncreasingDecreasingArray {
    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
        List<List<Integer>> lists = new ArrayList<>();
        int startIdx = 0;
        boolean isIncreasing = true;
        for (int i = 1; i <= A.size(); i++) {
            if (i == A.size() || (A.get(i - 1) > A.get(i) && isIncreasing) || (A.get(i - 1) < A.get(i) && !isIncreasing)) {
                lists.add(A.subList(startIdx, i));
                startIdx = i;
                if (isIncreasing) {
                    isIncreasing = false;
                } else {
                    isIncreasing = true;
                    Collections.reverse(lists.get(lists.size() - 1));
                }
            }
        }

        return SortedArraysMerge.mergeSortedArrays(lists);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
