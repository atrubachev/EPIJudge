package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class TreeExterior {

    private static List<BinaryTreeNode<Integer>>
    exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
        List<BinaryTreeNode<Integer>> exterior = new ArrayList<>();
        if (tree == null) {
            return exterior;
        }
        exterior.add(tree);
        if (!isLeaf(tree)) {
            exterior.addAll(computeEdge(tree.left, false));
            exterior.addAll(computeLeaves(tree));
            exterior.addAll(computeEdge(tree.right, true));
        }
        return exterior;
    }

    private static List<BinaryTreeNode<Integer>> computeEdge(BinaryTreeNode<Integer> tree, boolean isMirrored) {
        List<BinaryTreeNode<Integer>> exterior = new ArrayList<>();
        BinaryTreeNode<Integer> node = tree;
        while (node != null && !isLeaf(node)) {
            exterior.add(node);
            if (isMirrored) {
                node = node.right != null ? node.right : node.left;
            } else {
                node = node.left != null ? node.left : node.right;
            }
        }
        if (isMirrored) {
            Collections.reverse(exterior);
        }
        return exterior;
    }

    private static List<BinaryTreeNode<Integer>> computeLeaves(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<>();
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        nodes.addFirst(tree);
        while (!nodes.isEmpty()) {
            BinaryTreeNode<Integer> node = nodes.removeFirst();
            if (node == null) {
                continue;
            }
            if (isLeaf(node)) {
                result.add(node);
            }
            nodes.addFirst(node.right);
            nodes.addFirst(node.left);
        }
        return result;
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return node.left == null && node.right == null;
    }

    private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
            throws TestFailure {
        if (L.contains(null)) {
            throw new TestFailure("Resulting list contains null");
        }
        List<Integer> output = new ArrayList<>();
        for (BinaryTreeNode<Integer> l : L) {
            output.add(l.data);
        }
        return output;
    }

    @EpiTest(testDataFile = "tree_exterior.tsv")
    public static List<Integer>
    exteriorBinaryTreeWrapper(TimedExecutor executor,
                              BinaryTreeNode<Integer> tree) throws Exception {
        List<BinaryTreeNode<Integer>> result =
                executor.run(() -> exteriorBinaryTree(tree));

        return createOutputList(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
