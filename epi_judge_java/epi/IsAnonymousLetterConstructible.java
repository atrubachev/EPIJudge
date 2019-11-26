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
        for (Character ch : magazineText.toCharArray()) {
            if (!letterCharFreq.containsKey(ch)) {
                continue;
            }
            int freq = letterCharFreq.get(ch) - 1;
            if (freq == 0) {
                letterCharFreq.remove(ch);
                if (letterCharFreq.isEmpty()) {
                    break;
                }
            } else {
                letterCharFreq.put(ch, freq);
            }
        }
        return letterCharFreq.isEmpty();
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
