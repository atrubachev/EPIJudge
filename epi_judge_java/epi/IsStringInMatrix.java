package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsStringInMatrix {
    static class Attempt {
        int row, col, offset;

        Attempt(int row, int col, int offset) {
            this.row = row;
            this.col = col;
            this.offset = offset;
        }
    }

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {
        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).size(); col++) {
                if (grid.get(row).get(col).equals(pattern.get(0))) {
                    if (isPatternContainedInGrid(grid, row, col, pattern, 0, new HashSet<>())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isPatternContainedInGrid(List<List<Integer>> grid, int row, int col,
                                                    List<Integer> pattern, int offset, Set<Attempt> cache) {
        if (pattern.size() == offset) {
            return true;
        }
        if (row >= grid.size() || row < 0 || col >= grid.get(row).size() || col < 0
                || cache.contains(new Attempt(row, col, offset))) {
            return false;
        }

        if (grid.get(row).get(col).equals(pattern.get(offset))
                && (isPatternContainedInGrid(grid, row - 1, col, pattern, offset + 1, cache)
                || isPatternContainedInGrid(grid, row + 1, col, pattern, offset + 1, cache)
                || isPatternContainedInGrid(grid, row, col - 1, pattern, offset + 1, cache)
                || isPatternContainedInGrid(grid, row, col + 1, pattern, offset + 1, cache))) {
            return true;
        }

        cache.add(new Attempt(row, col, offset));
        return false;
    }


    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
