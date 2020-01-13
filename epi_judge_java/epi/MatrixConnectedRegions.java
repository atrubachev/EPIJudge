package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MatrixConnectedRegions {
    private static final List<Point> SHIFTS = List.of(new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1));

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void flipColor(int row, int col, List<List<Boolean>> matrix) {
        Deque<Point> queue = new LinkedList<>();
        queue.addFirst(new Point(row, col));
        boolean color = matrix.get(row).get(col);
        matrix.get(row).set(col, !color);
        while (!queue.isEmpty()) {
            Point currPoint = queue.removeLast();
            for (Point shift : SHIFTS) {
                Point nextPoint = new Point(currPoint.row + shift.row, currPoint.col + shift.col);
                if (isValid(nextPoint, matrix, color)) {
                    matrix.get(nextPoint.row).set(nextPoint.col, !color);
                    queue.addFirst(nextPoint);
                }
            }
        }
    }

    private static boolean isValid(Point point, List<List<Boolean>> matrix, boolean color) {
        return point.row >= 0 && point.col >= 0 && point.row < matrix.size() && point.col < matrix.get(point.row).size() &&
                matrix.get(point.row).get(point.col) == color;
    }


    @EpiTest(testDataFile = "painting.tsv")
    public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                       int x, int y,
                                                       List<List<Integer>> image)
            throws Exception {
        List<List<Boolean>> B = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            B.add(new ArrayList<>());
            for (int j = 0; j < image.get(i).size(); j++) {
                B.get(i).add(image.get(i).get(j) == 1);
            }
        }

        executor.run(() -> flipColor(x, y, B));

        image = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            image.add(new ArrayList<>());
            for (int j = 0; j < B.get(i).size(); j++) {
                image.get(i).add(B.get(i).get(j) ? 1 : 0);
            }
        }

        return image;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
