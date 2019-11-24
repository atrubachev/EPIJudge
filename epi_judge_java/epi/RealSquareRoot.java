package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RealSquareRoot {
    @EpiTest(testDataFile = "real_square_root.tsv")

    public static double squareRoot(double x) {
        double l = 1.0;
        double r = x;
        if (x < 1.0) {
            l = x;
            r = 1.0;
        }
        while (compare(l, r) < 0) {
            double m = l + (r - l) / 2;
            double mSquared = m * m;
            if (compare(mSquared, x) == 0) {
                return m;
            } else if (compare(mSquared, x) > 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    private static int compare(double a, double b) {
        final double epsilon = 0.000001;
        double diff = (a - b) / b;
        if (diff < -epsilon) {
            return -1;
        } else if (diff > epsilon) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
