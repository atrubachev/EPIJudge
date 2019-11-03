package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeLevelOrder {
    @EpiTest(testDataFile = "tree_level_order.tsv")

    public static List<List<Integer>>
    binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        Deque<NodeWithDepth> nodes = new LinkedList<>();
        if (tree != null) {
            nodes.add(new NodeWithDepth(tree, 0));
        }
        List<List<Integer>> result = new ArrayList<>();

        while (!nodes.isEmpty()) {
            NodeWithDepth node = nodes.poll();
            if (result.size() - 1 < node.depth) {
                result.add(new ArrayList<>());
            }
            result.get(node.depth).add(node.node.data);
            if (node.node.left != null) {
                nodes.add(new NodeWithDepth(node.node.left, node.depth + 1));
            }
            if (node.node.right != null) {
                nodes.add(new NodeWithDepth(node.node.right, node.depth + 1));
            }
        }

        return result;
    }

    public static class NodeWithDepth {
        BinaryTreeNode<Integer> node;
        Integer depth;

        public NodeWithDepth(BinaryTreeNode node, Integer depth) {
            this.node = node;
            this.depth = depth;
        }
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
