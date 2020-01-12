package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchMaze {
    private static final List<Coordinate> SHIFTS = List.of(new Coordinate(-1, 0), new Coordinate(+1, 0),
            new Coordinate(0, -1), new Coordinate(0, 1));

    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coordinate that = (Coordinate) o;
            if (x != that.x || y != that.y) {
                return false;
            }
            return true;
        }
    }

    public enum Color {WHITE, BLACK}

    public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        maze.get(start.x).set(start.y, Color.BLACK);
        dfs(maze, start, end, path);
        Collections.reverse(path);
        return path;
    }

    private static boolean dfs(List<List<Color>> maze, Coordinate move, Coordinate end, List<Coordinate> path) {
        if (move.equals(end)) {
            path.add(move);
            return true;
        }

        for (Coordinate shift : SHIFTS) {
            Coordinate nextMove = new Coordinate(move.x + shift.x, move.y + shift.y);
            if (isValidMove(maze, nextMove)) {
                maze.get(nextMove.x).set(nextMove.y, Color.BLACK);
                if (dfs(maze, nextMove, end, path)) {
                    path.add(move);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidMove(List<List<Color>> maze, Coordinate move) {
        if (move.x >= 0 && move.y >= 0 && move.x < maze.size() && move.y < maze.get(move.x).size() &&
                maze.get(move.x).get(move.y) == Color.WHITE) {
            return true;
        }
        return false;
    }

    public static boolean pathElementIsFeasible(List<List<Integer>> maze,
                                                Coordinate prev, Coordinate cur) {
        if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
                cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
            return false;
        }
        return cur.x == prev.x + 1 && cur.y == prev.y ||
                cur.x == prev.x - 1 && cur.y == prev.y ||
                cur.x == prev.x && cur.y == prev.y + 1 ||
                cur.x == prev.x && cur.y == prev.y - 1;
    }

    @EpiTest(testDataFile = "search_maze.tsv")
    public static boolean searchMazeWrapper(List<List<Integer>> maze,
                                            Coordinate s, Coordinate e)
            throws TestFailure {
        List<List<Color>> colored = new ArrayList<>();
        for (List<Integer> col : maze) {
            List<Color> tmp = new ArrayList<>();
            for (Integer i : col) {
                tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
            }
            colored.add(tmp);
        }
        List<Coordinate> path = searchMaze(colored, s, e);
        if (path.isEmpty()) {
            return s.equals(e);
        }

        if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
            throw new TestFailure("Path doesn't lay between start and end points");
        }

        for (int i = 1; i < path.size(); i++) {
            if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
                throw new TestFailure("Path contains invalid segments");
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchMaze.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
