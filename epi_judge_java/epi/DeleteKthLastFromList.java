package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {
    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

    // Assumes L has at least k nodes, deletes the k-th last node in L.
    public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
        ListNode<Integer> newHead = new ListNode<>(-1, L);
        ListNode<Integer> fast = L;

        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }

        ListNode<Integer> slow = newHead;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return newHead.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
