package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DoTerminatedListsOverlap {

    public static ListNode<Integer>
    overlappingNoCycleLists(ListNode<Integer> l1, ListNode<Integer> l2) {

        int k1 = length(l1);
        int k2 = length(l2);

        if (k1 > k2) {
            l1 = skipFirstN(l1, k1 - k2);
        } else {
            l2 = skipFirstN(l2, k2 - k1);
        }

        while (l1 != null && l2 != null && l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }

        return l1;
    }

    private static int length(ListNode<Integer> l) {
        int k = 0;
        while (l != null) {
            k++;
            l = l.next;
        }
        return k;
    }

    private static ListNode<Integer> skipFirstN(ListNode<Integer> l, int n) {
        while (l != null && n > 0) {
            n--;
            l = l.next;
        }
        return l;
    }

    @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
    public static void
    overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                   ListNode<Integer> l1, ListNode<Integer> common)
            throws Exception {
        if (common != null) {
            if (l0 != null) {
                ListNode<Integer> i = l0;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l0 = common;
            }

            if (l1 != null) {
                ListNode<Integer> i = l1;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l1 = common;
            }
        }

        final ListNode<Integer> finalL0 = l0;
        final ListNode<Integer> finalL1 = l1;
        ListNode<Integer> result =
                executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

        if (result != common) {
            throw new TestFailure("Invalid result");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
