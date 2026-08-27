package leetCode.java;

import java.util.*;

//Shortest Path Bellman–Ford Algorithm O(kE) O(V), E = flights.length, V = n
class Solution787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 其實就是在考Bellman–Ford收縮幾次
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0; // 起點
        for (int i = 0; i < k + 1; ++i) {
            int[] oldPrices = prices.clone(); // clone一份避免污染
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int p = flight[2];
                if (oldPrices[from] != Integer.MAX_VALUE) {
                    prices[to] = Math.min(prices[to], oldPrices[from] + p);
                }
            }
        }
        return (prices[dst] == Integer.MAX_VALUE) ? -1 : prices[dst];
    }
}
/* n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
          0   1   2   3     k
prices =  0  INF INF INF
          0  100 INF INF    0
		  0  100 200 700    1
		  0  100 200 400    2
*/