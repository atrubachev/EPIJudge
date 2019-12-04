package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {
    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

    public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
        List<Integer> intersection = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i).equals(B.get(j))) {
                if (intersection.isEmpty() || !intersection.get(intersection.size() - 1).equals(A.get(i))) {
                    intersection.add(A.get(i));
                }
                i++;
                j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return intersection;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
