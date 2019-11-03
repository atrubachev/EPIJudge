package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeLevelOrder {
    @EpiTest(testDataFile = "tree_level_order.tsv")

    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<>();
        if (tree != null) {
            nodes.add(tree);
        }
        List<List<Integer>> result = new ArrayList<>();

        while (!nodes.isEmpty()) {
            Deque<BinaryTreeNode<Integer>> nextNodes = new LinkedList<>();
            List<Integer> values = new ArrayList<>();
            result.add(values);
            while (!nodes.isEmpty()) {
                BinaryTreeNode<Integer> node = nodes.poll();
                values.add(node.data);
                if (node.left != null) {
                    nextNodes.add(node.left);
                }
                if (node.right != null) {
                    nextNodes.add(node.right);
                }
            }
            nodes = nextNodes;
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
