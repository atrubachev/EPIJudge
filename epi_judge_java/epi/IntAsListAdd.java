package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {
    @EpiTest(testDataFile = "int_as_list_add.tsv")

    public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                  ListNode<Integer> L2) {
        ListNode<Integer> resultHead = new ListNode<>(0, null);
        ListNode<Integer> result = resultHead;
        int carry = 0;
        while (L1 != null || L2 != null || carry != 0) {
            int sum = carry;
            if (L1 != null) {
                sum += L1.data;
                L1 = L1.next;
            }
            if (L2 != null) {
                sum += L2.data;
                L2 = L2.next;
            }
            result.next = new ListNode<>(sum % 10, null);
            result = result.next;
            carry = sum / 10;
        }

        return resultHead.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
