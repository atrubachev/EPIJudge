package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class MinimumPointsCoveringIntervals {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")

    public static Integer findMinimumVisits(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> a.right - b.right);
        int numVisits = 0;
        int lastVisitTime = -1;
        for (Interval interval : intervals) {
            if (interval.left > lastVisitTime) {
                lastVisitTime = interval.right;
                numVisits++;
            }
        }
        return numVisits;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
