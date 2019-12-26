package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class Combinations {
    @EpiTest(testDataFile = "combinations.tsv")

    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        directedCombinations(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private static void directedCombinations(int n, int k, int offset, ArrayList<Integer> partialCombination,
                                             List<List<Integer>> result) {
        if (k == partialCombination.size()) {
            result.add(new ArrayList<>(partialCombination));
            return;
        }
        int numRemaining = k - partialCombination.size();
        for (int i = offset; i <= n && numRemaining <= n - 1 + 1; i++) {
            partialCombination.add(i);
            directedCombinations(n, k, i + 1, partialCombination, result);
            partialCombination.remove(partialCombination.size() - 1);
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                expected.sort(new LexicographicalListComparator<>());
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
            };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Combinations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
