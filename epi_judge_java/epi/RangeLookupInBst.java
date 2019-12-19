package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RangeLookupInBst {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    @EpiTest(testDataFile = "range_lookup_in_bst.tsv")

    public static List<Integer> rangeLookupInBst(BstNode<Integer> tree, Interval interval) {
        List<Integer> result = new ArrayList<>();
        BstNode<Integer> curr = tree;
        Deque<BstNode<Integer>> queue = new LinkedList<>();
        while (curr != null || !queue.isEmpty()) {
            while (curr != null) {
                queue.addFirst(curr);
                if (curr.data.compareTo(interval.left) < 0) {
                    break;
                }
                curr = curr.left;
            }
            BstNode<Integer> node = queue.removeFirst();
            if (node.data.compareTo(interval.right) > 0) {
                break;
            }
            if (node.data.compareTo(interval.left) >= 0 && node.data.compareTo(interval.right) <= 0) {
                result.add(node.data);
            }
            curr = node.right;
        }

        return result;
    }

    public static void rangeLookupInBstHelper(BstNode<Integer> tree,
                                              Interval interval,
                                              List<Integer> result) {
        if (tree == null) {
            return;
        }
        if (interval.left <= tree.data && tree.data <= interval.right) {
            // tree.data lies in the interval.
            rangeLookupInBstHelper(tree.left, interval, result);
            result.add(tree.data);
            rangeLookupInBstHelper(tree.right, interval, result);
        } else if (interval.left > tree.data) {
            rangeLookupInBstHelper(tree.right, interval, result);
        } else { // interval.right >= tree.data
            rangeLookupInBstHelper(tree.left, interval, result);
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RangeLookupInBst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
