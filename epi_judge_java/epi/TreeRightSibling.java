package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeRightSibling {
    public static class BinaryTreeNode<T> {
        public T data;
        public BinaryTreeNode<T> left, right;
        public BinaryTreeNode<T> next = null; // Populates this field.

        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }

    private static void constructRightSibling(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> currNodes = new LinkedList<>();
        if (tree == null) {
            return;
        }
        currNodes.add(tree);
        while (!currNodes.isEmpty()) {
            Deque<BinaryTreeNode<Integer>> nextNodes = new LinkedList<>();
            BinaryTreeNode<Integer> prev = null;
            while (!currNodes.isEmpty()) {
                BinaryTreeNode<Integer> curr = currNodes.removeLast();
                curr.next = prev;
                prev = curr;
                if (curr.right != null) {
                    nextNodes.addFirst(curr.right);
                }
                if (curr.left != null) {
                    nextNodes.addFirst(curr.left);
                }
            }
            currNodes = nextNodes;
        }

    }

    private static BinaryTreeNode<Integer> cloneTree(BinaryTree<Integer> original) {
        if (original == null) {
            return null;
        }
        BinaryTreeNode<Integer> cloned = new BinaryTreeNode<>(original.data);
        cloned.left = cloneTree(original.left);
        cloned.right = cloneTree(original.right);
        return cloned;
    }

    @EpiTest(testDataFile = "tree_right_sibling.tsv")
    public static List<List<Integer>>
    constructRightSiblingWrapper(TimedExecutor executor, BinaryTree<Integer> tree)
            throws Exception {
        BinaryTreeNode<Integer> cloned = cloneTree(tree);

        executor.run(() -> constructRightSibling(cloned));

        List<List<Integer>> result = new ArrayList<>();
        BinaryTreeNode<Integer> levelStart = cloned;
        while (levelStart != null) {
            List<Integer> level = new ArrayList<>();
            BinaryTreeNode<Integer> levelIt = levelStart;
            while (levelIt != null) {
                level.add(levelIt.data);
                levelIt = levelIt.next;
            }
            result.add(level);
            levelStart = levelStart.left;
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeRightSibling.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
