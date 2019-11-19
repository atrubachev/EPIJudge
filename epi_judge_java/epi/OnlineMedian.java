package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {
    private static final int INITIAL_SIZE = 16;

    private static List<Double> onlineMedian(Iterator<Integer> sequence) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(INITIAL_SIZE);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(INITIAL_SIZE, Collections.reverseOrder());
        List<Double> result = new ArrayList<>();
        while (sequence.hasNext()) {
            Integer n = sequence.next();
            if (minHeap.isEmpty()) {
                minHeap.add(n);
            } else {
                if (n >= minHeap.peek()) {
                    minHeap.add(n);
                } else {
                    maxHeap.add(n);
                }
            }

            if (minHeap.size() < maxHeap.size()) {
                minHeap.add(maxHeap.remove());
            } else if (minHeap.size() - 1 > maxHeap.size()) {
                maxHeap.add(minHeap.remove());
            }

            if (minHeap.size() == maxHeap.size()) {
                result.add((minHeap.peek() + maxHeap.peek()) / 2.0);
            } else {
                result.add(Double.valueOf(minHeap.peek()));
            }
        }

        return result;
    }

    @EpiTest(testDataFile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
        return onlineMedian(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
