package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvenOddListMerge {
    @EpiTest(testDataFile = "even_odd_list_merge.tsv")

    public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
        if (L == null) {
            return L;
        }

        ListNode<Integer> even = new ListNode<>(-1, null);
        ListNode<Integer> newHead = even;
        ListNode<Integer> odd = new ListNode<>(-1, null);
        ListNode<Integer> oddHead = odd;
        int count = 0;
        while (L != null) {
            if (count % 2 == 0) {
                even.next = L;
                even = even.next;
            } else {
                odd.next = L;
                odd = odd.next;
            }
            L = L.next;
            count++;
        }

        even.next = oddHead.next;
        odd.next = null;

        return newHead.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
