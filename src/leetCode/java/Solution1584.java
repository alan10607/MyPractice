package leetCode.java;

//Expand O(n^2) O(1)

import java.util.*;

//MST Kruskal's Algorithm O(n^2 * logn) O(n^2), Union-Find時間複雜度為O(ElogE), 但此處E=n^2, 建立edges的空間複雜度O(E)要大於parents的O(V)
class Solution1584 {
    public int minCostConnectPoints(int[][] points) {
        //1 edges, O(n^2)
        List<int[]> edges = new ArrayList<>();//<[A點, B點, 距離], ...>
        for(int i=0; i<points.length; i++){
            for(int j = i + 1; j<points.length; j++){//計算半個正方形就可
                //manhattan distance = |xi - xj| + |yi - yj|
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{i, j, dist});
            }
        }

        //2 Union-Find 連接點, O(ElogE)
        int res = 0;
        int visitCount = 1;//一開始連接兩點, 其餘增加一點
        int[] parents = new int[points.length];
        Arrays.fill(parents, -1);
        Collections.sort(edges, (a, b) -> a[2] - b[2]);//依距離小到大
        for(int[] edge : edges){
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);
            if(a != b){
                res += edge[2];
                parents[a] = b;
                if(++visitCount == points.length)
                    break;//已經全連上
            }
        }
        return res;
    }

    public int find(int node, int[] parents){
        if(parents[node] == -1) return node;
        return parents[node] = find(parents[node], parents);
    }
}