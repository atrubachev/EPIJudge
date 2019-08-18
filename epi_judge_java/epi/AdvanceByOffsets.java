package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class AdvanceByOffsets {
    /**
     * Time complexity O(n)
     * Space complexity O(1)
     *
     * {3,3,1,0,2,0,1}
     * furthest = 0; last = 6
     * i = 0; furthest = max(3 + 0, 0) = 3
     * i = 1; furthest = max(3 + 1, 3) = 4
     * i = 2; furthest = max(1 + 2, 4) = 4
     * i = 3; furthest = max(0 + 3, 4) = 4
     * i = 4; furthest = max(2 + 4, 4) = 6
     * furthest = 6 < last = 6
     *
     */
    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthest = 0;
        int last = maxAdvanceSteps.size() - 1;

        for (int i = 0; i <= furthest && furthest < last; i++) {
            furthest = Math.max(maxAdvanceSteps.get(i) + i, furthest);
        }

        return furthest >= last;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
