package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeSieve {
    @EpiTest(testDataFile = "prime_sieve.tsv")
    // Given n, return all primes up to and including n.
    public static List<Integer> generatePrimes(int n) {
        if (n < 2) return Collections.emptyList();

        List<Integer> primes = new ArrayList<>();
        boolean[] notPrime = new boolean[n+1];

        for (int i = 2; i <= n; i++) {
            if (!notPrime[i]) {
                primes.add(i);
                for (int j = i + i; j <= n; j += i) notPrime[j] = true;
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
