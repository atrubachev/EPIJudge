package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

public class Permutations {
    @EpiTest(testDataFile = "permutations.tsv")

    public static List<List<Integer>> permutations(List<Integer> A) {
        return computePermutations(A, 0);
    }

    private static List<List<Integer>> computePermutations(List<Integer> A, int currIdx) {
        List<List<Integer>> result = new ArrayList<>();
        if (currIdx + 1 >= A.size()) {
            List<Integer> perm = new LinkedList<>();
            perm.add(A.get(currIdx));
            result.add(perm);
            return result;
        }
        for (int i = currIdx; i < A.size(); i++) {
            Collections.swap(A, currIdx, i);
            for (List<Integer> permutation : computePermutations(A, currIdx + 1)) {
                permutation.add(0, A.get(currIdx));
                result.add(permutation);
            }
            Collections.swap(A, currIdx, i);
        }
        return result;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                for (List<Integer> l : expected) {
                    Collections.sort(l);
                }
                expected.sort(new LexicographicalListComparator<>());
                for (List<Integer> l : result) {
                    Collections.sort(l);
                }
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
            };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Permutations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
