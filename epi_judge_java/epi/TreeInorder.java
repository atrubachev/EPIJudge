package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeInorder {
    @EpiTest(testDataFile = "tree_inorder.tsv")

    public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode<Integer> curr = tree;
        while (!nodes.isEmpty() || curr != null) {
            if (curr != null) {
                nodes.addFirst(curr);
                curr = curr.left;
            } else {
                curr = nodes.removeFirst();
                result.add(curr.data);
                curr = curr.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
