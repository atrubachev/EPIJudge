package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SortedArrayRemoveDups {
    /**
     * Time complexity O(n)
     * Space complexity O(1)
     *
     * @param A is a sorted list of Integers
     * @return Returns the number of valid entries after deletion.
     */
    private static int deleteDuplicates(List<Integer> A) {
        if (A.isEmpty()) return 0;

        int slow = 1;
        for (int fast = 1; fast < A.size(); fast++) {
            if (!A.get(fast).equals(A.get(slow - 1))) {
                Collections.swap(A, fast, slow++);
            }
        }
        return slow;
    }

    @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
    public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                        List<Integer> A)
            throws Exception {
        int end = executor.run(() -> deleteDuplicates(A));
        return A.subList(0, end);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
