package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class CalendarRendering {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Event {
        public int start, finish;

        public Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    private static class Endpoint {
        public int time;
        public boolean isStart;

        Endpoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }

    @EpiTest(testDataFile = "calendar_rendering.tsv")

    public static int findMaxSimultaneousEvents(List<Event> A) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> finishes = new ArrayList<>();

        for (Event event : A) {
            starts.add(event.start);
            finishes.add(event.finish);
        }

        starts.sort(Integer::compare);
        finishes.sort(Integer::compare);

        int i = 0, j = 0, max = 0, count = 0;
        while (i < starts.size() && j < finishes.size()) {
            if (starts.get(i) <= finishes.get(j)) {
                count++;
                i++;
                max = Math.max(max, count);
            } else {
                count--;
                j++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
