package epi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSortedArrays {
    static class ArrayEntry {
        int currIdx;
        List<Integer> array;

        ArrayEntry(int currIdx, List<Integer> array) {
            this.currIdx = currIdx;
            this.array = array;
        }
    }

    private static List<Integer> merge(List<List<Integer>> sortedArrays) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(),
                Comparator.comparingInt(o -> o.array.get(o.currIdx)));
        for (List<Integer> array : sortedArrays) {
            minHeap.add(new ArrayEntry(0, array));
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ArrayEntry currMin = minHeap.poll();
            while (minHeap.peek() != null && currMin.array.get(currMin.currIdx) <= minHeap.peek().array.get(minHeap.peek().currIdx)) {
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
        List<List<Integer>> sortedArrays = List.of(
                List.of(0, 2, 4),
                List.of(1, 3, 5),
                List.of(3, 6)
        );
        List<Integer> result = merge(sortedArrays);
        System.out.println(result.toString());
    }
}
