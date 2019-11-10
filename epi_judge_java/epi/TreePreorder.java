package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreePreorder {
    @EpiTest(testDataFile = "tree_preorder.tsv")

    public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode<Integer> node = tree;
        if (node != null) {
            nodes.addFirst(node);
        }
        while (!nodes.isEmpty()) {
            node = nodes.removeFirst();
            result.add(node.data);
            if (node.right != null) {
                nodes.addFirst(node.right);
            }
            if (node.left != null) {
                nodes.addFirst(node.left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
