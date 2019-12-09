package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class IsTreeABst {

    private static class NodeWithLimits {
        BinaryTreeNode<Integer> node;
        Integer smaller, bigger;

        public NodeWithLimits(BinaryTreeNode<Integer> node, Integer smaller, Integer bigger) {
            this.node = node;
            this.smaller = smaller;
            this.bigger = bigger;
        }
    }

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> root) {
        Deque<NodeWithLimits> queue = new LinkedList<>();
        queue.addFirst(new NodeWithLimits(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        while (!queue.isEmpty()) {
            NodeWithLimits node = queue.removeLast();
            if (node.node == null) {
                continue;
            }
            if ((node.smaller.compareTo(node.node.data) > 0) || (node.bigger.compareTo(node.node.data) < 0)) {
                return false;
            }
            queue.addFirst(new NodeWithLimits(node.node.left, node.smaller, node.node.data));
            queue.addFirst(new NodeWithLimits(node.node.right, node.node.data, node.bigger));
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
