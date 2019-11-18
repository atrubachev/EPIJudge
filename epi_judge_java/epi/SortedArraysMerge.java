package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
    private static class ArrayEntry {
        int currIdx;
        List<Integer> array;

        ArrayEntry(int currIdx, List<Integer> array) {
            this.currIdx = currIdx;
            this.array = array;
        }
    }

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(),
                Comparator.comparingInt(o -> o.array.get(o.currIdx)));
        for (List<Integer> array : sortedArrays) {
            minHeap.add(new ArrayEntry(0, array));
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ArrayEntry currMin = minHeap.poll();
            while (minHeap.peek() != null &&
                    currMin.array.get(currMin.currIdx) <= minHeap.peek().array.get(minHeap.peek().currIdx)) {
                result.add(currMin.array.get(currMin.currIdx));
                currMin.currIdx++;
                if (currMin.currIdx == currMin.array.size()) {
                    break;
                }
            }
            if (minHeap.peek() == null) {
                for (int i = currMin.currIdx; i < currMin.array.size(); i++) {
                    result.add(currMin.array.get(i));
                }
            } else if (currMin.currIdx < currMin.array.size()) {
                minHeap.add(new ArrayEntry(currMin.currIdx, currMin.array));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
