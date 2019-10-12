package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class ValidIpAddresses {
    @EpiTest(testDataFile = "valid_ip_addresses.tsv")

    public static List<String> getValidIpAddress(String s) {
        if (s.length() < 4 || s.length() > 12) {
            return new ArrayList<>();
        }

        return findValidIps(3, 0, s);
    }

    private static List<String> findValidIps(int dots, int startPos, String s) {
        List<String> ips = new ArrayList<>();

        if (startPos >= s.length()) {
            return new ArrayList<>();
        }

        if (dots == 0) {
            String octet = s.substring(startPos);
            if (!isValidOctet(octet)) {
                return ips;
            }
            return List.of(octet);
        }

        for (int i = startPos; i < startPos + 3 && i < s.length(); i++) {
            String octet = s.substring(startPos, i + 1);
            if (!isValidOctet(octet)) {
                continue;
            }
            List<String> ipParts = findValidIps(dots - 1, i + 1, s);
            for (String ipPart : ipParts) {
                ips.add(octet + "." + ipPart);
            }
        }

        return ips;
    }

    private static boolean isValidOctet(String octet) {
        if (octet.length() > 1 && octet.startsWith("0")) {
            return false;
        }
        int n = Integer.parseInt(octet);
        return n >= 0 && n < 256;
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
