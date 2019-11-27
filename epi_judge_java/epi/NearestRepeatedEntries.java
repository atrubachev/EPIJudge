package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

    public static int findNearestRepetition(List<String> paragraph) {
        Map<String, Integer> wordToIdx = new HashMap<>();
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < paragraph.size(); i++) {
            String word = paragraph.get(i);
            if (wordToIdx.containsKey(word)) {
                distance = Math.min(distance, i - wordToIdx.get(word));
            }
            wordToIdx.put(word, i);
        }

        if (distance == Integer.MAX_VALUE) {
            return -1;
        }
        return distance;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
