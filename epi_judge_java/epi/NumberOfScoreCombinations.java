package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class NumberOfScoreCombinations {
    @EpiTest(testDataFile = "number_of_score_combinations.tsv")

    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
        int[][] numCombinationsForScore = new int[individualPlayScores.size()][finalScore + 1];
        for (int combination = 0; combination < individualPlayScores.size(); combination++) {
            numCombinationsForScore[combination][0] = 1;
            for (int score = 1; score <= finalScore; score++) {
                int withoutPlay = 0;
                if (combination - 1 >= 0) {
                    withoutPlay = numCombinationsForScore[combination - 1][score];
                }
                int withPlay = 0;
                if (score >= individualPlayScores.get(combination)) {
                    withPlay = numCombinationsForScore[combination][score - individualPlayScores.get(combination)];
                }
                numCombinationsForScore[combination][score] = withoutPlay + withPlay;
            }
        }
        return numCombinationsForScore[individualPlayScores.size() - 1][finalScore];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
