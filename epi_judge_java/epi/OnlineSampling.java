package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class OnlineSampling {

    // Assumption: there are at least k elements in the stream.
    private static List<Integer> onlineRandomSample(Iterator<Integer> stream,
                                                    int k) {

        var sample = new ArrayList<Integer>(k);

        for (var i = 0; stream.hasNext() && i < k; i++) sample.add(stream.next());

        var random = new Random();
        var seen = k;

        while (stream.hasNext()) {
            var x = stream.next();
            seen++;
            var i = random.nextInt(seen);
            if (i < k) sample.set(i, x);
        }

        return sample;
    }

    private static boolean onlineRandomSampleRunner(TimedExecutor executor,
                                                    List<Integer> A, int k)
            throws Exception {
        List<List<Integer>> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 1000000; ++i) {
                results.add(onlineRandomSample(A.iterator(), k));
            }
        });

        int totalPossibleOutcomes =
                RandomSequenceChecker.binomialCoefficient(A.size(), k);
        Collections.sort(A);
        List<List<Integer>> combinations = new ArrayList<>();
        for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(A.size(), k);
             ++i) {
            combinations.add(
                    RandomSequenceChecker.computeCombinationIdx(A, A.size(), k, i));
        }
        List<Integer> sequence = new ArrayList<>();
        for (List<Integer> result : results) {
            Collections.sort(result);
            sequence.add(combinations.indexOf(result));
        }
        return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
                sequence, totalPossibleOutcomes, 0.01);
    }

    @EpiTest(testDataFile = "online_sampling.tsv")
    public static void onlineRandomSampleWrapper(TimedExecutor executor,
                                                 List<Integer> stream, int k)
            throws Exception {
        RandomSequenceChecker.runFuncWithRetries(
                () -> onlineRandomSampleRunner(executor, stream, k));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OnlineSampling.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
