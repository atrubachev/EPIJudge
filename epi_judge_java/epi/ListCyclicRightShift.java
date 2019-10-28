package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ListCyclicRightShift {
    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

    public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L, int k) {
        if (L == null) {
            return L;
        }

        int length = length(L);
        int shift = k % length;
        ListNode<Integer> oldHead = L;
        ListNode<Integer> newHead = cutListOnKthNode(L, length - shift);
        return joinLists(newHead, oldHead);
    }

    private static ListNode<Integer> joinLists(ListNode<Integer> L1, ListNode<Integer> L2) {
        if (L1 == null) {
            return L2;
        }

        ListNode<Integer> node = L1;
        while (node.next != null) {
            node = node.next;
        }
        node.next = L2;

        return L1;
    }

    private static ListNode<Integer> cutListOnKthNode(ListNode<Integer> L, int k) {
        ListNode<Integer> tail = L;
        for (int i = 1; i < k; i++) {
            tail = tail.next;
        }
        ListNode<Integer> head = tail.next;
        tail.next = null;
        return head;
    }

    private static int length(ListNode<Integer> L) {
        int length = 0;
        ListNode<Integer> node = L;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
