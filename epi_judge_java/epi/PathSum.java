package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PathSum {
    @EpiTest(testDataFile = "path_sum.tsv")

    public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                     int remainingWeight) {
        if (tree == null) {
            return false;
        }

        remainingWeight -= tree.data;

        if (remainingWeight == 0 && tree.left == null && tree.right == null) {
            return true;
        }

        return hasPathSum(tree.left, remainingWeight) || hasPathSum(tree.right, remainingWeight);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PathSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
