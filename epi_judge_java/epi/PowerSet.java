package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PowerSet {
    @EpiTest(testDataFile = "power_set.tsv")

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        return generatePowerSet(inputSet, 0);
    }

    private static List<List<Integer>> generatePowerSet(List<Integer> inputSet, int idx) {
        List<List<Integer>> result = new ArrayList<>();
        if (idx == inputSet.size()) {
            result.add(new ArrayList<>());
            return result;
        }
        for (List<Integer> subSet : generatePowerSet(inputSet, idx + 1)) {
            result.add(subSet);
            List<Integer> newSubSet = new ArrayList<>(subSet);
            newSubSet.add(inputSet.get(idx));
            result.add(newSubSet);
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
                        .runFromAnnotations(args, "PowerSet.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
