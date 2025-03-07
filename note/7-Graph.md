# Graph


## Topological Sort 拓撲排序
- https://leetcode.com/problems/course-schedule/
- https://leetcode.com/problems/course-schedule-ii/


## Bipartition 二分圖
- https://leetcode.com/problems/is-graph-bipartite/
- https://leetcode.com/problems/possible-bipartition/


## Union-Find 併查集
- https://leetcode.com/problems/redundant-connection/
- https://leetcode.com/problems/satisfiability-of-equality-equations/
- *https://leetcode.com/problems/graph-valid-tree/
- *https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
- https://leetcode.com/problems/accounts-merge/


## Minimum Spanning Tree MST 最小生成樹
- https://leetcode.com/problems/min-cost-to-connect-all-points/


Minimum Spanning Tree(無向):
- Prim Algorithm
    - Greedy, pq
    - 維護一mst矩陣, 任意起點, 選擇連距離這個union最近的點, 找最近的點可以用pq優化
- Kruskal Algorithm
    - Sort, Union-Find
    - 排序所有邊長, 從最小路經開始, 透過Union-Find連成同一union


## Shortest Path Tree 最短路徑樹
- https://leetcode.com/problems/network-delay-time/
- https://leetcode.com/problems/swim-in-rising-water/


Shortest Path Tree(有向):
- Dijkstra Algorithm
    - Greedy, pq
    - 從起點開始找能夠到達的最近距離(pq), 不可用負環
- Bellman-Ford Algorithm
    - DP, for loop
    - 跑V次收縮dist[], 預設不可到達為INT_MAX, 結束再收縮可以檢測負環
