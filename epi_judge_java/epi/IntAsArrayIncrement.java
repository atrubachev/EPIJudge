package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {
    /**
     * Time complexity O(n)
     * Space complexity O(1)
     */
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> A) {
        int carry = 1;
        for (int i = A.size() - 1; i >= 0; i--) {
            int value = A.get(i) + carry;
            A.set(i, value % 10);
            carry = value / 10;
        }

        if (carry > 0) {
            A.add(0, carry);
        }

        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
