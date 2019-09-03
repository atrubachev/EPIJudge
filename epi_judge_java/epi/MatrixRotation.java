package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixRotation {

    private static void rotateMatrix(List<List<Integer>> squareMatrix) {
        int n = squareMatrix.size() - 1;
        for (int i = 0; i < squareMatrix.size() / 2; i++) {
            for (int j = i; j < n - i; j++) {
                int t1 = squareMatrix.get(n - j).get(i);
                int t2 = squareMatrix.get(n - i).get(n - j);
                int t3 = squareMatrix.get(j).get(n - i);
                int t4 = squareMatrix.get(i).get(j);
                squareMatrix.get(i).set(j, t1);
                squareMatrix.get(n - j).set(i, t2);
                squareMatrix.get(n - i).set(n - j, t3);
                squareMatrix.get(j).set(n - i, t4);
            }
        }
    }

    @EpiTest(testDataFile = "matrix_rotation.tsv")
    public static List<List<Integer>>
    rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
        rotateMatrix(squareMatrix);
        return squareMatrix;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
