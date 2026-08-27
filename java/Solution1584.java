package leetCode.java;

//Expand O(n^2) O(1)

import java.util.*;

//MST Prim's Algorithm O(n^2) O(n), 這題的prim不用建立edge, 因為任何一個點都存在雙向路線(直接用for loop + continue去除連上的)
class Solution1584 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, res = 0;
        int[] mst = new int[n]; // mst[i]表示i點距離整個mst的最近距離
        Arrays.fill(mst, Integer.MAX_VALUE); // MAX_VALUE表示無法到達
        mst[0] = 0; // 選擇任意起點
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) { // 每次加入一個新的node, 要n次
            // 找到最小mst
            int node = -1;
            for (int j = 0; j < n; ++j) {
                if (visited[j]) {
                    continue;
                }
                if (node == -1 || mst[j] < mst[node]) {
                    node = j;
                }
            }

            // 連上這個node
            visited[node] = true;
            res += mst[node];

            // 以這個新加入mst的點更新mst array
            for (int j = 0; j < n; ++j) {
                if (visited[j]) {
                    continue;
                }
                int dist = Math.abs(points[node][0] - points[j][0]) + Math.abs(points[node][1] - points[j][1]);
                mst[j] = Math.min(mst[j], dist);
            }
        }
        return res;
    }
}
/* 
Prim, 隨機找一個點為起點加入生成樹, 然後更新mst(每個點與生成樹的距離)
下一個點是mst最小的, 代表他離整棵樹是最近的

ex: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]

                i=      0   1   2   3   4
node=0, res=0   mst=    0,  4,  13, 7,  7
node=1, res=4   mst=    0,  4,  9,  3,  7
node=3, res=7   mst=    0,  4,  9,  3,  4
node=4, res=11  mst=    0,  4,  9,  3,  4
node=2, res=20  mst=    0,  4,  9,  3,  4
*/


//MST Kruskal's Algorithm O(n^2 * logn) O(n^2), Union-Find時間複雜度為O(ElogE), 但此處E=n^2, 建立edges的空間複雜度O(E)要大於parents的O(V)
class Solution {
    public int minCostConnectPoints(int[][] points) {
        // 本題較不適合用Kruskal, 因為要自己建立所有edges
        int n = points.length;
        List<int[]> edges = new ArrayList<>(); // <[i點, j點, i點與j點的距離]>
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{i, j, dist});
            }
        }

        Collections.sort(edges, (a, b) -> a[2] - b[2]); // 依照兩點距離排列

        //union-find
        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        int res = 0;
        for (int[] edge : edges) {
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);
            if (a == b) { // 已經有連上mst, 不要再重複建立
                continue;
            }

            parents[a] = b;
            res += edge[2];
        }
        return res;
    }

    public int find(int node, int[] parents) {
        if (parents[node] == -1) {
            return node;
        }
        return parents[node] = find(parents[node], parents);
    }
}
/* 
Kruskal, 建立所有點與點的距離關係edges, 並從最短的開始連

ex: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]

edges=[[i點, j點, i點與j點的距離]
=[[1,3,3], [0,1,4], [3,4,4], [0,3,7], [0,4,7], [1,4,7], [1,2,9], [2,3,10], [0,2,13], [2,4,14]]

union-find:
a=1, b=3, dist=3, res=3, mst成員:1, 3
a=0, b=1, dist=4, res=7, mst成員:1, 3, 0
a=3, b=4, dist=4, res=11, mst成員:1, 3, 0, 4
a=1, b=2, dist=9, res=20, mst成員:1, 3, 0, 4, 2

*/