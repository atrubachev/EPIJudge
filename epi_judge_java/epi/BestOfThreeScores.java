package epi;

import java.util.*;

public class BestOfThreeScores {

    private static String findStudentWithHighestBestOfThreeScores(Iterator<Object> nameScoreData) {
        Map<String, PriorityQueue<Integer>> studentScores = new HashMap<>();
        while (nameScoreData.hasNext()) {
            String studentId = (String) nameScoreData.next();
            Integer score = (Integer) nameScoreData.next();
            PriorityQueue<Integer> scores = studentScores.get(studentId);
            if (Objects.isNull(scores)) {
                scores = new PriorityQueue<>();
                studentScores.put(studentId, scores);
            }
            scores.add(score);
            if (scores.size() > 3) {
                scores.poll();
            }
        }

        String topStudent = "no such student";
        int currentSumOfTopThreeScores = 0;
        for (Map.Entry<String, PriorityQueue<Integer>> entry : studentScores.entrySet()) {
            if (entry.getValue().size() < 3) {
                continue;
            }
            int sum = 0;
            for (Integer integer : entry.getValue()) {
                sum += integer;
            }
            if (sum > currentSumOfTopThreeScores) {
                topStudent = entry.getKey();
                currentSumOfTopThreeScores = sum;
            }
        }

        return topStudent;
    }


    public static void main(String[] argv) {
        Iterator iter = List.of(
                "student1", 10,
                "student1", 20,
                "student2", 1,
                "student1", 30,
                "student3", 100,
                "student2", 2,
                "student3", 200,
                "student2", 3
        ).iterator();
        System.out.println(findStudentWithHighestBestOfThreeScores(iter));
    }
}
