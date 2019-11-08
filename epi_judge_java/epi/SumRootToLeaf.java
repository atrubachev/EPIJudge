package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {
    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return sumHelper(tree, 0);
    }

    private static int sumHelper(BinaryTreeNode<Integer> node, int partialSum) {
        if (node == null) {
            return 0;
        }
        partialSum = partialSum * 2 + node.data;
        if (node.left == null && node.right == null) {
            return partialSum;
        }
        return sumHelper(node.left, partialSum) + sumHelper(node.right, partialSum);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
