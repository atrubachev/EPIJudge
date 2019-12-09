package epi;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class TreeNodeWithParent {
        TreeNode node;
        TreeNodeWithParent parent;

        TreeNodeWithParent(TreeNode node, TreeNodeWithParent parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Deque<TreeNodeWithParent> queue = new LinkedList<>();
        if (root != null) {
            queue.addFirst(new TreeNodeWithParent(root, null));
        }

        while (!queue.isEmpty()) {
            Deque<TreeNodeWithParent> next = new LinkedList<>();
            Deque<TreeNodeWithParent> prev = queue;
            while (!queue.isEmpty()) {
                TreeNodeWithParent node = queue.removeLast();
                if (node.node.left != null) {
                    next.addFirst(new TreeNodeWithParent(node.node.left, node));
                }
                if (node.node.right != null) {
                    next.addFirst(new TreeNodeWithParent(node.node.right, node));
                }
            }
            if (next.isEmpty()) {
                return findParent(prev);
            }
            queue = next;
        }

        return null;
    }

    private TreeNode findParent(Deque<TreeNodeWithParent> nodes) {
        Set<TreeNodeWithParent> parents = new HashSet<>();
        while (parents.size() != 1) {
            for (TreeNodeWithParent node : nodes) {
                parents.add(node.parent);
            }
        }
        return parents.iterator().next().node;
    }
}