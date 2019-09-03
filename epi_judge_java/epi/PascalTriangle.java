package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    @EpiTest(testDataFile = "pascal_triangle.tsv")

    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> matrix = new ArrayList<>(numRows);

        for (int row = 0; row < numRows; row++) {
            matrix.add(new ArrayList<>(numRows));
            for (int col = 0; col < row + 1; col++) {
                if (col == 0 || row == col) {
                    matrix.get(row).add(1);
                } else {
                    matrix.get(row).add(matrix.get(row - 1).get(col - 1) + matrix.get(row - 1).get(col));
                }
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
