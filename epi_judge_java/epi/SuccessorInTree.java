package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.Objects;

public class SuccessorInTree {

    private static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        if (Objects.isNull(node)) {
            return null;
        }

        BinaryTree<Integer> iter = node;

        if (Objects.nonNull(iter.right)) {
            iter = iter.right;
            while (Objects.nonNull(iter.left)) {
                iter = iter.left;
            }
            return iter;
        }

        while (Objects.nonNull(iter.parent) && iter.equals(iter.parent.right)) {
            iter = iter.parent;
        }

        return iter.parent;
    }

    @EpiTest(testDataFile = "successor_in_tree.tsv")
    public static int findSuccessorWrapper(TimedExecutor executor,
                                           BinaryTree<Integer> tree, int nodeIdx)
            throws Exception {
        BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

        BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

        return result == null ? -1 : result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
