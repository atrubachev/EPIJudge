package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class FindSalaryThreshold {
    @EpiTest(testDataFile = "find_salary_threshold.tsv")

    public static double findSalaryCap(int targetPayroll, List<Integer> currentSalaries) {
        Collections.sort(currentSalaries);
        double salarySum = 0;
        for (int i = 0; i < currentSalaries.size(); i++) {
            double sumOfSalaryCap = currentSalaries.get(i) * (currentSalaries.size() - i);
            if (salarySum + sumOfSalaryCap >= targetPayroll) {
                return (targetPayroll - salarySum) / (currentSalaries.size() - i);
            }
            salarySum += currentSalaries.get(i);
        }
        return -1.0;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "FindSalaryThreshold.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
