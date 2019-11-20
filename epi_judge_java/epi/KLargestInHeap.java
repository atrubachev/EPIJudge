package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

public class KLargestInHeap {

    static class Node {
        int idx, value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }

    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
        List<Integer> result = new ArrayList<>(k);
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> b.value - a.value);
        if (!A.isEmpty()) {
            minHeap.add(new Node(0, A.get(0)));
        }
        while (!minHeap.isEmpty() && result.size() != k) {
            Node node = minHeap.remove();
            result.add(node.value);
            int leftIdx = node.idx * 2 + 1;
            int rightIdx = node.idx * 2 + 2;
            if (leftIdx < A.size()) {
                minHeap.add(new Node(leftIdx, A.get(leftIdx)));
            }
            if (rightIdx < A.size()) {
                minHeap.add(new Node(rightIdx, A.get(rightIdx)));
            }
        }

        return result;
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
