package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class NextPermutation {
    @EpiTest(testDataFile = "next_permutation.tsv")
    public static List<Integer> nextPermutation(List<Integer> perm) {
        if (perm.size() < 1) return perm;

        // find the index of an element before the longest decreasing sequence
        int i = perm.size() - 1;
        while (i - 1 >= 0 && perm.get(i - 1) >= perm.get(i)) i--;
        // there is no the next permutation
        if (i == 0) return Collections.emptyList();
        i--;
        // find the index of element bigger than `i` in the longest decreasing sequence
        int j = perm.size() - 1;
        while (j >= 0 && perm.get(i) >= perm.get(j)) j--;

        Collections.swap(perm, i, j);
        // revert the longest decreasing sequence
        i++;
        int k = perm.size() - 1;
        while (i < k) {
            Collections.swap(perm, i, k);
            i++;
            k--;
        }

        return perm;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
