package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {
    /**
     * Time complexity O(n)
     * Space complexity O(1)
     *
     * Page 52
     */
    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        Double maxProfit = 0.0;
        Double minBuy = Double.MAX_VALUE;

        for (Double price : prices) {
            Double profit = price - minBuy;
            maxProfit = Math.max(profit, maxProfit);
            minBuy = Math.min(price, minBuy);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
