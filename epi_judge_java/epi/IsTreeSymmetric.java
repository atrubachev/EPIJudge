package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class IsTreeSymmetric {
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")

    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return true;
        }

        Deque<BinaryTreeNode<Integer>> left = new LinkedList<>();
        Deque<BinaryTreeNode<Integer>> right = new LinkedList<>();
        left.addLast(tree.left);
        right.addLast(tree.right);

        while (!left.isEmpty() && !right.isEmpty()) {
            BinaryTreeNode<Integer> leftNode = left.removeFirst();
            BinaryTreeNode<Integer> rightNode = right.removeFirst();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null || !leftNode.data.equals(rightNode.data)) {
                return false;
            }
            left.addLast(leftNode.left);
            right.addLast(rightNode.right);
            left.addLast(leftNode.right);
            right.addLast(rightNode.left);
        }

        return left.isEmpty() && right.isEmpty();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
