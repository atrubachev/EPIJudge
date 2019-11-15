package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeConnectLeaves {

    private static List<BinaryTreeNode<Integer>> createListOfLeaves(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<>();
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        if (tree != null) {
            nodes.addFirst(tree);
        }
        while (!nodes.isEmpty()) {
            BinaryTreeNode<Integer> node = nodes.removeFirst();
            if (node.left == null && node.right == null) {
                result.add(node);
            }
            if (node.right != null) {
                nodes.addFirst(node.right);
            }
            if (node.left != null) {
                nodes.addFirst(node.left);
            }
        }

        return result;
    }

    @EpiTest(testDataFile = "tree_connect_leaves.tsv")
    public static List<Integer>
    createListOfLeavesWrapper(TimedExecutor executor,
                              BinaryTreeNode<Integer> tree) throws Exception {
        List<BinaryTreeNode<Integer>> result =
                executor.run(() -> createListOfLeaves(tree));

        if (result.stream().anyMatch(x -> x == null || x.data == null)) {
            throw new TestFailure("Result can't contain null");
        }

        List<Integer> extractedRes = new ArrayList<>();
        for (BinaryTreeNode<Integer> x : result) {
            extractedRes.add(x.data);
        }
        return extractedRes;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeConnectLeaves.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
