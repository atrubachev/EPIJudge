package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;

public class IsValidSudoku {
    @EpiTest(testDataFile = "is_valid_sudoku.tsv")

    // Check if a partially filled matrix has any conflicts.
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        // check rows
        for (var r = 0; r < partialAssignment.size(); r++) {
            if (hasDuplicate(partialAssignment, r, 0, r + 1, partialAssignment.size())) return false;
        }
        // check cols
        for (var c = 0; c < partialAssignment.size(); c++) {
            if (hasDuplicate(partialAssignment, 0, c, partialAssignment.size(), c + 1)) return false;
        }

        for (var r = 0; r < partialAssignment.size() - 2; r += 3) {
            for (var c = 0; c < partialAssignment.size() - 2; c += 3) {
                if (hasDuplicate(partialAssignment, r, c, r + 3, c + 3)) return false;
            }
        }

        return true;
    }

    private static boolean hasDuplicate(List<List<Integer>> A, int rowStart, int colStart, int rowStop, int colStop) {
        var seen = new HashSet<Integer>();
        for (var r = rowStart; r < rowStop; r++) {
            for (var c = colStart; c < colStop; c++) {
                var v = A.get(r).get(c);
                if (seen.contains(v)) return true;
                if (v != 0) seen.add(v);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
