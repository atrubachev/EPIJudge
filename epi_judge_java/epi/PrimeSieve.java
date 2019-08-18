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
        primes.add(2);
        for (int i = 3; i <= n; i++) {
            if ((i % 2 != 0) && isPrime(primes, i)) primes.add(i);
        }

        return primes;
    }

    private static boolean isPrime(List<Integer> primes, int n) {
        for (int i = 0; i < primes.size() && i < Math.sqrt(n); i++) {
            if (n % primes.get(i) == 0) {
                return false;
            }
        }
        return true;
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
