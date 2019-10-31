package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public class EvaluateRpn {
    @EpiTest(testDataFile = "evaluate_rpn.tsv")

    public static int eval(String expression) {
        Deque<Integer> terms = new LinkedList<>();
        Set<String> ops = Set.of("/", "*", "+", "-");
        for (String element : expression.split(",")) {
            if (ops.contains(element)) {
                terms.push(evalOp(terms.pop(), terms.pop(), element));
            } else {
                terms.push(Integer.parseInt(element));
            }
        }

        return terms.pop();
    }

    private static Integer evalOp(int a, int b, String op) {
        switch (op) {
            case "/":
                return b / a;
            case "*":
                return b * a;
            case "+":
                return b + a;
            default:
                return b - a;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
