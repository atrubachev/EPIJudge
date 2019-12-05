package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntervalAdd {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Interval interval = (Interval) o;

            if (left != interval.left) {
                return false;
            }
            return right == interval.right;
        }

        @Override
        public String toString() {
            return "[" + left + ", " + right + "]";
        }
    }

    @EpiTest(testDataFile = "interval_add.tsv")

    public static List<Interval> addInterval(List<Interval> disjointIntervals, Interval newInterval) {
        List<Interval> intervals = new ArrayList<>();
        int i = 0;

        while (i < disjointIntervals.size() && disjointIntervals.get(i).right < newInterval.left) {
            intervals.add(disjointIntervals.get(i++));
        }

        while (i < disjointIntervals.size() && newInterval.right >= disjointIntervals.get(i).left) {
            newInterval = new Interval(
                    Math.min(disjointIntervals.get(i).left, newInterval.left),
                    Math.max(disjointIntervals.get(i).right, newInterval.right)
            );
            i++;
        }
        intervals.add(newInterval);

        intervals.addAll(disjointIntervals.subList(i, disjointIntervals.size()));

        return intervals;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntervalAdd.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
