package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LargestRectangleUnderSkyline {
    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

    public static int calculateLargestRectangle(List<Integer> heights) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.size(); i++) {
            // equals height
            if (!stack.isEmpty() && i < heights.size() && heights.get(i).equals(heights.get(stack.getFirst()))) {
                stack.removeFirst();
                stack.addFirst(i);
            }

            // current height is less than the previous or it is the end
            while (!stack.isEmpty() && isLessTallOrEnd(heights, i, stack.getFirst())) {
                int height = heights.get(stack.removeFirst());
                int width = i;
                if (!stack.isEmpty()) {
                    width = i - stack.getFirst() - 1;
                }
                maxArea = Math.max(maxArea, height * width);
            }

            stack.addFirst(i);
        }

        return maxArea;
    }

    private static boolean isLessTallOrEnd(List<Integer> heights, int currIdx, int lastIdx) {
        if (currIdx < heights.size()) {
            return heights.get(currIdx).compareTo(heights.get(lastIdx)) < 0;
        }
        return true;
    }

    private static int getIdxOfTallestBuilding(List<Integer> heights) {
        int maxValue = 0;
        int idxOfMax = 0;
        for (int i = 0; i < heights.size(); i++) {
            if (heights.get(i) > maxValue) {
                maxValue = heights.get(i);
                idxOfMax = i;
            }
        }
        return idxOfMax;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
