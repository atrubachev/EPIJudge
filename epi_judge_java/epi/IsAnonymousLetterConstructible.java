package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

    public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        Map<Character, Integer> letterCharFreq = computeCharFreq(letterText);
        Map<Character, Integer> magazineCharFreq = computeCharFreq(magazineText);
        for (Map.Entry<Character, Integer> entry : letterCharFreq.entrySet()) {
            if (entry.getValue().compareTo(magazineCharFreq.getOrDefault(entry.getKey(), 0)) > 0) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> computeCharFreq(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (Character ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        return freq;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
