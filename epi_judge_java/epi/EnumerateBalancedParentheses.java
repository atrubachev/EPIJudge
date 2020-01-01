package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class EnumerateBalancedParentheses {
    @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")

    public static List<String> generateBalancedParentheses(int numPairs) {
        List<String> result = new ArrayList<>();
        generateBalancedParentheses(numPairs, numPairs, "", result);
        return result;
    }

    private static void generateBalancedParentheses(int numLeftPairsNeeded, int numRightPairsNeeded,
                                                    String validPrefix, List<String> result) {
        if (numLeftPairsNeeded == 0 && numRightPairsNeeded == 0) {
            result.add(validPrefix);
            return;
        }
        if (numLeftPairsNeeded > 0) {
            generateBalancedParentheses(numLeftPairsNeeded - 1, numRightPairsNeeded, validPrefix + "(", result);
        }
        if (numLeftPairsNeeded < numRightPairsNeeded) {
            generateBalancedParentheses(numLeftPairsNeeded, numRightPairsNeeded - 1, validPrefix + ")", result);
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
