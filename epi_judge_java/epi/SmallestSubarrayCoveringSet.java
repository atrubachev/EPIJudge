package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringSet {

    // Represent subarray by starting and ending indices, inclusive.
    private static class Subarray {
        public Integer start;
        public Integer end;

        Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph, Set<String> keywords) {
        Map<String, Integer> keywordsToCover = new HashMap<>();
        for (String keyword : keywords) {
            keywordsToCover.put(keyword, 1);
        }

        Subarray result = new Subarray(-1, -1);
        int remainingToCover = keywords.size();
        for (int left = 0, right = 0; right < paragraph.size(); right++) {
            if (keywordsToCover.containsKey(paragraph.get(right))) {
                int keywordCount = keywordsToCover.get(paragraph.get(right)) - 1;
                keywordsToCover.put(paragraph.get(right), keywordCount);
                if (keywordCount >= 0) {
                    remainingToCover--;
                }
            }

            while (remainingToCover == 0) {
                if ((result.start == -1 && result.end == -1) || (right - left < result.end - result.start)) {
                    result.start = left;
                    result.end = right;
                }
                if (keywordsToCover.containsKey(paragraph.get(left))) {
                    int keywordCount = keywordsToCover.get(paragraph.get(left)) + 1;
                    keywordsToCover.put(paragraph.get(left), keywordCount);
                    if (keywordCount > 0) {
                        remainingToCover++;
                    }
                }
                left++;
            }
        }

        return result;
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper(
            TimedExecutor executor, List<String> paragraph, Set<String> keywords)
            throws Exception {
        Set<String> copy = new HashSet<>(keywords);

        Subarray result = executor.run(
                () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

        if (result.start < 0 || result.start >= paragraph.size() ||
                result.end < 0 || result.end >= paragraph.size() ||
                result.start > result.end)
            throw new TestFailure("Index out of range");

        for (int i = result.start; i <= result.end; i++) {
            copy.remove(paragraph.get(i));
        }

        if (!copy.isEmpty()) {
            throw new TestFailure("Not all keywords are in the range");
        }
        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
