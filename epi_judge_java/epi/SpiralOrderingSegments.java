package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderingSegments {
    @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        var n = squareMatrix.size();
        var spiralOrder = new ArrayList<Integer>(n * n);
        var moves = new Move[]{new Move(0, 1), new Move(1, 0), new Move(0, -1), new Move(-1, 0)};
        var dir = 0;
        var r = 0;
        var c = 0;

        for (var i = 0; i < n * n; i++) {
            spiralOrder.add(squareMatrix.get(r).get(c));
            squareMatrix.get(r).set(c, 0);
            var nR = r + moves[dir].r;
            var nC = c + moves[dir].c;
            if (nR < 0 || nR >= n || nC < 0 || nC >= n || squareMatrix.get(nR).get(nC) == 0) {
                dir = (dir + 1) % 4;
                nR = r + moves[dir].r;
                nC = c + moves[dir].c;
            }
            r = nR;
            c = nC;
        }

        return spiralOrder;
    }

    static class Move {
        int r;
        int c;

        Move(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
