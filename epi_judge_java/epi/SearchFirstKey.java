package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {
    @EpiTest(testDataFile = "search_first_key.tsv")

    public static int searchFirstOfK(List<Integer> A, int k) {
        int l = 0;
        int h = A.size() - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (A.get(m) < k) {
                l = m + 1;
            } else if (A.get(m) > k) {
                h = m - 1;
            } else {
                h = m;
                while (l < h) {
                    m = l + (h - l) / 2;
                    if (A.get(m) != k) {
                        l = m + 1;
                    } else {
                        h = m;
                    }
                }
                return l;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
