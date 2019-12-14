package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

    public static class ArrayData implements Comparable<ArrayData> {
        public int val;
        public int idx;

        public ArrayData(int idx, int val) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ArrayData o) {
            int result = Integer.compare(val, o.val);
            if (result == 0) {
                result = Integer.compare(idx, o.idx);
            }
            return result;
        }
    }

    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        List<Integer> headsOfArrays = new ArrayList<>(sortedArrays.size());
        for (int i = 0; i < sortedArrays.size(); i++) {
            headsOfArrays.add(0);
        }

        int minDistance = Integer.MAX_VALUE;
        NavigableSet<ArrayData> minElements = new TreeSet<>();

        for (int i = 0; i < sortedArrays.size(); i++) {
            List<Integer> sortedArray = sortedArrays.get(i);
            minElements.add(new ArrayData(i, sortedArray.get(0)));
        }

        while (true) {
            minDistance = Math.min(minDistance, minElements.last().val - minElements.first().val);
            ArrayData currMin = minElements.first();
            int nextMinIdx = headsOfArrays.get(currMin.idx) + 1;
            headsOfArrays.set(currMin.idx, nextMinIdx);
            List<Integer> sortedArray = sortedArrays.get(currMin.idx);
            if (headsOfArrays.get(currMin.idx) >= sortedArray.size()) {
                return minDistance;
            }
            minElements.pollFirst();
            minElements.add(new ArrayData(currMin.idx, sortedArray.get(nextMinIdx)));
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
