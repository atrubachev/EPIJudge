package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {
    @EpiTest(testDataFile = "sorted_lists_merge.tsv")
    //@include
    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                        ListNode<Integer> L2) {
        ListNode<Integer> root = new ListNode<>(-1, null);
        ListNode<Integer> node = root;

        while (L1 != null && L2 != null) {
            if (L1.data < L2.data) {
                node.next = L1;
                L1 = L1.next;
            } else {
                node.next = L2;
                L2 = L2.next;
            }
            node = node.next;
        }
        if (L1 != null) {
            node.next = L1;
        } else {
            node.next = L2;
        }

        return root.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
