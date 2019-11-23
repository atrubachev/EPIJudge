package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntSquareRoot {
    @EpiTest(testDataFile = "int_square_root.tsv")

    public static int squareRoot(int k) {
        long l = 0;
        long h = k;
        long result = l;
        while (l <= h) {
            long m = l + ((h - l) / 2);
            long mPow2 = m * m;
            if (mPow2 > k) {
                h = m - 1;
            } else {
                result = m;
                l = m + 1;
            }
        }
        return (int) (result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
