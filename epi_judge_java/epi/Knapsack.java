package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class Knapsack {
    @EpiUserType(ctorParams = {Integer.class, Integer.class})

    public static class Item {
        public Integer weight;
        public Integer value;

        public Item(Integer weight, Integer value) {
            this.weight = weight;
            this.value = value;
        }
    }

    @EpiTest(testDataFile = "knapsack.tsv")

    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
        int[][] cache = new int[items.size()][capacity + 1];
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                cache[i][j] = -1;
            }
        }
        return optimumSubjectToCapacity(items, items.size() - 1, capacity, cache);
    }

    private static int optimumSubjectToCapacity(List<Item> items, int itemIdx, int capacity, int[][] cache) {
        if (itemIdx < 0 || capacity <= 0) {
            return 0;
        }
        if (cache[itemIdx][capacity] == -1) {
            int withItem = 0;
            int itemWeight = items.get(itemIdx).weight;
            int itemValue = items.get(itemIdx).value;
            if (itemWeight <= capacity) {
                withItem = itemValue + optimumSubjectToCapacity(items, itemIdx - 1, capacity - itemWeight, cache);
            }
            int withOutItem = optimumSubjectToCapacity(items, itemIdx - 1, capacity, cache);
            cache[itemIdx][capacity] = Math.max(withItem, withOutItem);
        }
        return cache[itemIdx][capacity];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Knapsack.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
