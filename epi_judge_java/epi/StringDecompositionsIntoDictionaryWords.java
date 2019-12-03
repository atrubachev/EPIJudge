package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringDecompositionsIntoDictionaryWords {
    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")

    public static List<Integer> findAllSubstrings(String s, List<String> words) {
        List<Integer> result = new ArrayList<>();
        if (words.isEmpty()) {
            return result;
        }
        int wordSize = words.iterator().next().length();
        Map<String, Integer> wordFreq = computeStringFreq(words);
        for (int i = 0; i + wordSize * words.size() <= s.length(); i++) {
            if (matchAllWords(s, i, wordSize, words.size(), wordFreq)) {
                result.add(i);
            }
        }
        return result;
    }

    private static Map<String, Integer> computeStringFreq(List<String> words) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        return freq;
    }

    private static boolean matchAllWords(String s, int startIdx, int wordSize, int wordsNum, Map<String, Integer> wordFreq) {
        Map<String, Integer> currWordFreq = new HashMap<>();
        for (int i = 0; i < wordsNum; i++) {
            String word = s.substring(startIdx + i * wordSize, startIdx + (i + 1) * wordSize);
            if (!wordFreq.containsKey(word)) {
                return false;
            }
            currWordFreq.put(word, currWordFreq.getOrDefault(word, 0) + 1);
            if (wordFreq.get(word) < currWordFreq.get(word)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, "StringDecompositionsIntoDictionaryWords.java",
                        new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
