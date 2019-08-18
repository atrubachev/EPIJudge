package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        int sign = num1.get(0) * num2.get(0) > 0 ? 1 : -1;
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < num1.size() + num2.size(); i++) result.add(0);

        for (int i = num1.size() - 1; i >= 0; i--) {
            for (int j = num2.size() - 1; j >= 0; j--) {
                int value = result.get(i + j + 1) + num1.get(i) * num2.get(j);
                result.set(i + j, result.get(i + j) + value / 10);
                result.set(i + j + 1, value % 10);
            }
        }

        result = removeLeadingZeros(result);

        result.set(0, result.get(0) * sign);

        return result;
    }

    private static List<Integer> removeLeadingZeros(List<Integer> A) {
        int firstNotZero = 0;
        // find an index of the first not zero element
        while (firstNotZero < A.size() && A.get(firstNotZero) == 0) {
            firstNotZero++;
        }
        // all elements are zeros
        if (firstNotZero == A.size()) firstNotZero -= 1;

        return A.subList(firstNotZero, A.size());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
