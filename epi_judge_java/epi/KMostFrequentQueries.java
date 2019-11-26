package epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMostFrequentQueries {
    private static List<String> computeTopK(List<String> queries, int k) {
        Map<String, Integer> queryToFreq = new HashMap<>();
        for (String query : queries) {
            queryToFreq.put(query, queryToFreq.getOrDefault(query, 0) + 1);
        }
        List<String> sortedQueries = new ArrayList<>(queryToFreq.keySet());
        sortedQueries.sort((o1, o2) -> Integer.compare(queryToFreq.get(o2), queryToFreq.get(o1)));
        return sortedQueries.subList(0, k);
    }

    public static void main(String[] args) {
        List<String> queries = List.of("1", "1", "2", "3", "2", "5");
        List<String> top = computeTopK(queries, 2);
        System.out.println(top);
    }
}
