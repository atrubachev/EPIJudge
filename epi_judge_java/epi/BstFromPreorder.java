package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder {
    private static int rootIdx;

    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        rootIdx = 0;
        return rebuildBst(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static BstNode<Integer> rebuildBst(List<Integer> sequence, int min, int max) {
        if (rootIdx >= sequence.size()) {
            return null;
        }
        Integer root = sequence.get(rootIdx);
        if (root < min || root > max) {
            return null;
        }
        rootIdx++;
        BstNode<Integer> left = rebuildBst(sequence, min, root);
        BstNode<Integer> right = rebuildBst(sequence, root, max);
        return new BstNode<>(root, left, right);
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
