package leetCode.javaCode;

import java.util.*;

/**
 *  Advanced Graphs
 */
public class NeetCode150_AdvancedGraph {

    //Time Complexity: O(E logE), Space Complexity: O(E), E = tickets.size()
    //Hierholzer Algorithm
    class Solution332 {
        public List<String> res = new ArrayList<>();

        public List<String> findItinerary(List<List<String>> tickets) {
            //從一個起點出發, 進行DFS, 但是每次沿著輔助邊移動到下一個點時, 同時需要刪除這個輔助邊

            //1 edges + sort(PriorityQueue, 因為要lexical order)
            Map<String, PriorityQueue<String>> edges = new HashMap<>();
            for(List<String> ticket : tickets){
                String from = ticket.get(0);
                String to = ticket.get(1);
                if(!edges.containsKey(from))
                    edges.put(from, new PriorityQueue<String>());

                edges.get(from).offer(to);
            }

            //2 DFS
            dfs("JFK", edges);

            return res;
        }

        public void dfs(String from, Map<String, PriorityQueue<String>> edges){
            while(edges.containsKey(from) && !edges.get(from).isEmpty()){
                String to = edges.get(from).poll();
                dfs(to, edges);
            }
            res.add(0, from);//逆序入棧, 從頭加入
        }
    }
    /*
    tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]

    adj list, 同時對下一個目標進行排序
    JFK -> ATL SFO
    SFO -> ATL
    ATL -> JFK SFO

    start from JFK
    JFK -> ATL -> JFK -> SFO -> ATL -> SFO
    如果DFS失敗, 那就重新backtracking就好了

    tickets = [["JFK","AAA"],["JFK","BBB"],["BBB","JFK"]]
    JKF -> AAA BBB
    BBB -> JFK
    或者可以逆序入棧, 只要進入死胡同, 就先加入到最後
    因為繼續搜尋其他非死胡同的路線都會回到原節點, 再把它們加入到前面, 就不影響結果
    */

    //MST, Kruskal's Algorithm, Prim's Algorithm
    class Solution1584 {
        //Minimum Spanning Tree (MST) 最小生成樹
        //Manhattan Distance = |x1 - x2| + |y1 - y2|

        //Time Complexity: O(n^2 logn), Space Complexity: O(n^2)
        public int minCostConnectPoints(int[][] points) {
            //Kruskal's Algorithm
            //整理所有可行的連線邊, 由邊長小開始連接點, 若形成環則跳過, 所有點都連上則終止

            //1 edges
            int n = points.length;
            List<int[]> edges = new ArrayList<>();//<[A點, B點, 距離], ...>
            for(int i=0; i<points.length; i++){
                for(int j = i + 1; j<points.length; j++){
                    int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    edges.add(new int[]{i, j, dist});
                }

            }

            //2 Union-Find
            int res = 0;
            int[] parents = new int[n];
            int[] level = new int[n];//union的深度/個數
            Arrays.fill(parents, -1);//預設自己是自己的父節點
            Arrays.fill(level, 1);//預設深度皆為1

            //從距離最小開始
            Collections.sort(edges, (a, b) -> a[2] - b[2]);
            for(int i=0; i<edges.size(); i++){
                int[] edge = edges.get(i);
                int a = find(edge[0], parents);
                int b = find(edge[1], parents);

                //只連接未union的部份, 否則成環無意義
                if(a != b){
                    res += edge[2];//計算總長度

                    //消滅較小的level
                    if(level[a] < level[b]){
                        parents[a] = b;//接到比較大的底下
                        level[b] += level[a];

                        if(level[b] == n)
                            break;//全連上
                    }else{
                        parents[b] = a;
                        level[a] += level[b];

                        if(level[a] == n)
                            break;//全連上
                    }
                }
            }

            return res;
        }

        public int find(int node, int[] parents){
            if(parents[node] == -1) return node;
            return find(parents[node], parents);
        }

        //Time Complexity: O(n^2), Space Complexity: O(n^2)
        public int minCostConnectPoints2(int[][] points) {
            //Prim's Algorithm
            //選定一起點, 尋找該點可連線到其他點的最短距離, 之後將已連上的點加入整體考量, 尋找下一個離已連接點群最近的未連接點

            //1 dist
            int n = points.length;
            int[][] dist = new int[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(i != j)
                        dist[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                }
            }

            //2 維護minDist array
            int[] minDist = new int[n];//已探訪點到其他點的最短距離
            int res = 0;

            //起點
            for(int i=0; i<minDist.length; i++){
                minDist[i] = dist[0][i];//設定初始最短距離, 即0到其他點的距離
            }
            minDist[0] = -1;//設定起點為已探訪

            for(int k=1; k<n; k++){
                //找出最短距離
                int nextPoint = -1;
                int min = Integer.MAX_VALUE;
                for(int i=0; i<minDist.length; i++){
                    //只查詢未訪問的
                    if(minDist[i] != -1 && minDist[i] < min){
                        nextPoint = i;
                        min = minDist[i];
                    }
                }

                minDist[nextPoint] = -1;//表示visited
                res += min;

                //更新已知距離
                for(int i=0; i<n; i++){
                    if(minDist[i] != -1)//只更新未訪問的
                        minDist[i] = Math.min(minDist[i], dist[nextPoint][i]);
                }
            }

            return res;
        }
        /*
        0----(5)----1
        |-(3)-2-(1)-|

        minDist = [-1,  5,  3],  visited = 0
        minDist = [-1,  5, -1],  visited = 0, 2
        minDist = [-1, -1, -1],  visited = 0, 2, 1
        */
    }

    //Time Complexity: O(E logE), Space Complexity: O(V + E), E = times.length, V = n
    //Shortest Path, Dijkstra's Algorithm
    class Solution743 {
        public int networkDelayTime(int[][] times, int n, int k) {
            //選定一起點, 尋找該點可連到的其他點, 依離起點的長度放入heap排序, 之後依照BFS走訪, 每次走訪都要更新該點離起點的距離

            //1 edges & length
            Map<Integer, List<int[]>> edges = new HashMap<>();//<A點, <[B點, 距離], ...>>
            for(int[] time : times){
                if(!edges.containsKey(time[0]))
                    edges.put(time[0], new ArrayList<int[]>());

                edges.get(time[0]).add(new int[]{time[1], time[2]});
            }

            //2 BFS
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);//依照距離小排到大
            Set<Integer> visited = new HashSet<>();
            pq.offer(new int[]{k, 0});//第一個點
            int res = 0;
            while(!pq.isEmpty()){
                int size = pq.size();
                for(int i=0; i<size; i++){
                    int[] node = pq.poll();

                    if(visited.contains(node[0]))
                        continue;//已經visited, 跳過

                    res = Math.max(res, node[1]);
                    visited.add(node[0]);

                    if(edges.containsKey(node[0]))
                        for(int[] edge : edges.get(node[0])){
                            pq.offer(new int[]{edge[0], edge[1] + node[1]});//距離更新加上這次的
                        }
                }
            }

            return visited.size() == n ? res : -1;
        }
    }
    /*
	times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
	start from 2, pq = [2, 0]
	res = 0, pq = [1, 1], [3, 1], visited = 2
	res = 1, pq = [3, 1], visited = 2, 1
	res = 1, pq = [4, 2], visited = 2, 1, 3
	res = 2, pq = empty, visited = 2, 1, 3, 4
	*/

    //Time Complexity: O(n^2 logn), Space Complexity: O(n^2), n = E, 時間複查度為整張表n^2 * logn(heap)
    //Dijkstra's Algorithm
    class Solution778 {
        public int swimInWater(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);//<[x, y, 深度], ...>
            heap.offer(new int[]{0, 0, grid[0][0]});
            boolean[][] visited = new boolean[m][n];
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            while(!heap.isEmpty()){
                int size = heap.size();
                for(int i=0; i<size; i++){
                    int[] water = heap.poll();
                    int x = water[0];
                    int y = water[1];
                    if(!visited[x][y]){
                        visited[x][y] = true;

                        if(x == m - 1 && y == n - 1)
                            return water[2];

                        for(int[] dir : dirs){
                            int nextX = x + dir[0];
                            int nextY = y + dir[1];
                            if(nextX < m && nextX >= 0 && nextY < n && nextY >= 0 && !visited[nextX][nextY])
                                heap.offer(new int[]{nextX, nextY, Math.max(water[2], grid[nextX][nextY])});//要更新成目前最高的水位
                        }
                    }
                }
            }
            return -1;
        }
    }

}