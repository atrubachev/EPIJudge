package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;

public class KLargestValuesInBst {
    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

    public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<BstNode<Integer>> stack = new LinkedList<>();
        BstNode<Integer> curr = tree;
        int count = 0;
        while ((curr != null || !stack.isEmpty()) && count < k) {
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.right;
            }
            curr = stack.removeFirst();
            count++;
            result.add(curr.data);
            curr = curr.left;
        }
        return result;
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
