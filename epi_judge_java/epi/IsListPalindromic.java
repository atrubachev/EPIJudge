package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {
    @EpiTest(testDataFile = "is_list_palindromic.tsv")

    public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
        if (L == null || L.next == null) {
            return true;
        }

        ListNode<Integer> half = findHalfHead(L);
        ListNode<Integer> reversedHalf = reverseList(half);

        ListNode<Integer> iter = L;
        ListNode<Integer> iterHalf = reversedHalf;
        while (iterHalf != null) {
            if (!iter.data.equals(iterHalf.data)) {
                return false;
            }
            iter = iter.next;
            iterHalf = iterHalf.next;
        }

        return true;
    }

    private static ListNode<Integer> reverseList(ListNode<Integer> L) {
        ListNode<Integer> dummyHead = new ListNode<>(-1, L);
        ListNode<Integer> iter = dummyHead.next;
        while (iter.next != null) {
            ListNode<Integer> temp = iter.next;
            iter.next = temp.next;
            temp.next = dummyHead.next;
            dummyHead.next = temp;
        }
        return dummyHead.next;
    }

    private static ListNode<Integer> findHalfHead(ListNode<Integer> L) {
        ListNode<Integer> slow = L;
        ListNode<Integer> fast = L;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast == null ? slow : slow.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
