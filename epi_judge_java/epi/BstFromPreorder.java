package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder {
    @EpiTest(testDataFile = "bst_from_preorder.tsv")

    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        return rebuildBst(preorderSequence, 0, preorderSequence.size());
    }

    private static BstNode<Integer> rebuildBst(List<Integer> sequence, int start, int end) {
        if (start >= end) {
            return null;
        }
        Integer root = sequence.get(start);
        int nextRootStart = start + 1;
        while (nextRootStart < end && root.compareTo(sequence.get(nextRootStart)) > 0) {
            nextRootStart++;
        }
        return new BstNode<>(root, rebuildBst(sequence, start + 1, nextRootStart), rebuildBst(sequence, nextRootStart, end));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
