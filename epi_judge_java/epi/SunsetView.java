package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView {
    private static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        Deque<Integer> buildingsWithSunset = new LinkedList<>();
        Deque<Integer> positionsOfBuildings = new LinkedList<>();
        int position = -1;
        while (sequence.hasNext()) {
            position++;
            int building = sequence.next();
            while (!buildingsWithSunset.isEmpty() && buildingsWithSunset.peek() <= building) {
                buildingsWithSunset.pop();
                positionsOfBuildings.pop();
            }
            buildingsWithSunset.push(building);
            positionsOfBuildings.push(position);
        }
        return new ArrayList<>(positionsOfBuildings);
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer>
    examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
