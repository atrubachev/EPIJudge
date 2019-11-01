package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class IsValidParenthesization {
    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

    public static boolean isWellFormed(String s) {
        Deque<Character> parenthesises = new LinkedList<>();
        Map<Character, Character> pairs = Map.of('}', '{', ')', '(', ']', '[');
        for (Character parenthesis : s.toCharArray()) {
            if (parenthesises.isEmpty() || !parenthesises.peek().equals(pairs.get(parenthesis))) {
                parenthesises.push(parenthesis);
            } else {
                parenthesises.pop();
            }
        }

        return parenthesises.isEmpty();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
