package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchRowColSortedMatrix {
    @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")

    public static boolean matrixSearch(List<List<Integer>> A, int x) {
        int row = 0;
        int col = A.get(row).size() - 1;
        while (row < A.size() && col >= 0) {
            int y = A.get(row).get(col);
            if (y == x) {
                return true;
            } else if (y < x) {
                row += 1;
            } else {
                col -= 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
