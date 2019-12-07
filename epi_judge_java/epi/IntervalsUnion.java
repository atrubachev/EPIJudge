package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntervalsUnion {

    public static class Interval implements Comparable<Interval> {
        public Endpoint left = new Endpoint();
        public Endpoint right = new Endpoint();

        @Override
        public String toString() {
            return "Interval{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }

        private static class Endpoint {
            public boolean isClosed;
            public int val;
        }

        @Override
        public int compareTo(Interval interval) {
            if (this.left.val != interval.left.val) {
                return Integer.compare(this.left.val, interval.left.val);
            }

            if (this.left.isClosed && !interval.left.isClosed) {
                return -1;
            }
            if (!this.left.isClosed && interval.left.isClosed) {
                return 1;
            }
            return 0;
        }

    }

    public static List<Interval> unionOfIntervals(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }

        Collections.sort(intervals);
        Interval curr = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);
            if (curr.right.val > next.left.val
                    || (curr.right.val == next.left.val && (curr.right.isClosed || next.left.isClosed))) {
                if (curr.right.val < next.right.val || (curr.right.val == next.right.val && next.right.isClosed)) {
                    curr.right = next.right;
                }
            } else {
                result.add(curr);
                curr = next;
            }
        }
        result.add(curr);
        return result;
    }

    @EpiUserType(
            ctorParams = {int.class, boolean.class, int.class, boolean.class})
    public static class FlatInterval {
        int leftVal;
        boolean leftIsClosed;
        int rightVal;
        boolean rightIsClosed;

        public FlatInterval(int leftVal, boolean leftIsClosed, int rightVal,
                            boolean rightIsClosed) {
            this.leftVal = leftVal;
            this.leftIsClosed = leftIsClosed;
            this.rightVal = rightVal;
            this.rightIsClosed = rightIsClosed;
        }

        public FlatInterval(Interval i) {
            if (i != null) {
                leftVal = i.left.val;
                leftIsClosed = i.left.isClosed;
                rightVal = i.right.val;
                rightIsClosed = i.right.isClosed;
            }
        }

        public Interval toInterval() {
            Interval i = new Interval();
            i.left.val = leftVal;
            i.left.isClosed = leftIsClosed;
            i.right.val = rightVal;
            i.right.isClosed = rightIsClosed;
            return i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            FlatInterval that = (FlatInterval) o;

            if (leftVal != that.leftVal) {
                return false;
            }
            if (leftIsClosed != that.leftIsClosed) {
                return false;
            }
            if (rightVal != that.rightVal) {
                return false;
            }
            return rightIsClosed == that.rightIsClosed;
        }

        @Override
        public int hashCode() {
            int result = leftVal;
            result = 31 * result + (leftIsClosed ? 1 : 0);
            result = 31 * result + rightVal;
            result = 31 * result + (rightIsClosed ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return "" + (leftIsClosed ? "<" : "(") + leftVal + ", " + rightVal +
                    (rightIsClosed ? ">" : ")");
        }
    }

    @EpiTest(testDataFile = "intervals_union.tsv")
    public static List<FlatInterval>
    unionIntervalWrapper(TimedExecutor executor, List<FlatInterval> intervals)
            throws Exception {
        List<Interval> casted = new ArrayList<>(intervals.size());
        for (FlatInterval in : intervals) {
            casted.add(in.toInterval());
        }

        List<Interval> result = executor.run(() -> unionOfIntervals(casted));

        intervals = new ArrayList<>(result.size());
        for (Interval i : result) {
            intervals.add(new FlatInterval(i));
        }
        return intervals;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntervalsUnion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
