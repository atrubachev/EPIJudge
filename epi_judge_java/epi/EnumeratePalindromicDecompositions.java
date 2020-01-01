package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class EnumeratePalindromicDecompositions {
    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")

    public static List<List<String>> palindromeDecompositions(String s) {
        List<List<String>> result = new ArrayList<>();
        palindromeDecompositions(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void palindromeDecompositions(String s, int offset, ArrayList<String> decomposition, List<List<String>> result) {
        if (offset == s.length()) {
            result.add(new ArrayList<>(decomposition));
            return;
        }
        for (int i = offset + 1; i <= s.length(); i++) {
            String elementOfDecomposition = s.substring(offset, i);
            if (isPolindrom(elementOfDecomposition)) {
                decomposition.add(elementOfDecomposition);
                palindromeDecompositions(s, i, decomposition, result);
                decomposition.remove(decomposition.size() - 1);
            }
        }
    }

    private static boolean isPolindrom(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<String>>, List<List<String>>> comp =
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
                        .runFromAnnotations(args, "EnumeratePalindromicDecompositions.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
