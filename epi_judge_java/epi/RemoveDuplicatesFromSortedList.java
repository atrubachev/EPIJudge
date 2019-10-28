package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {
    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

    public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
        ListNode<Integer> node = L;
        while (node != null) {
            ListNode<Integer> next = node.next;
            while (next != null && node.data == next.data) {
                next = next.next;
                node.next = next;
            }
            node = node.next;
        }

        return L;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
