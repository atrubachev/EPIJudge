package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {
    @EpiTest(testDataFile = "reverse_sublist.tsv")

    public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                   int finish) {

        if (L == null || start == finish) {
            return L;
        }

        ListNode<Integer> newHead = new ListNode<>(-1, L);
        ListNode<Integer> sublistHead = newHead;

        int k = 1;
        while (k++ < start) {
            sublistHead = sublistHead.next;
        }

        ListNode<Integer> node = sublistHead.next;
        while (start++ < finish) {
            ListNode<Integer> temp = node.next;
            node.next = temp.next;
            temp.next = sublistHead.next;
            sublistHead.next = temp;
        }

        return newHead.next;
    }

    private static ListNode<Integer> reverse(ListNode<Integer> L) {
        ListNode<Integer> node = L;
        ListNode<Integer> next = null;
        ListNode<Integer> prev = null;

        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
