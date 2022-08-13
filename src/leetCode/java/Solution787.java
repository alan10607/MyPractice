package leetCode.java;

import java.util.*;

//Shortest Path Bellman–Ford Algorithm O(kE) O(V), E = flights.length, V = n
class Solution787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //Bellman–Ford可以在負權環
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        prices[src] = 0;
        for(int i=0; i < k + 1; i++){//Arrays.copyOf(陣列, 長度)
            int[] newPrices = Arrays.copyOf(prices, prices.length);//記得要複製用來更新
            for(int[] flight : flights){
                int a = flight[0];
                int b = flight[1];
                int cost = flight[2];
                if(prices[a] < Integer.MAX_VALUE)
                    newPrices[b] = Math.min(newPrices[b], prices[a] + cost);
            }
            prices = newPrices;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}
/* n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
          0   1   2   3     k
prices =  0  INF INF INF
          0  100 INF INF    0
		  0  100 200 700    1
		  0  100 200 400    2
*/