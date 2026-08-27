package leetCode.java;

import java.util.*;

//Shortest Path Dijkstra Algorithm O(E + ElogV) O(V + E), V = n, E = times.length
//收集edges需要O(E) O(E), 跑pq while要O(ElogV) O(V)
class Solution743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>(); // <from, <[to1, cost1], [to2, cost2], ...>>
        for (int[] time : times) {
            edges.putIfAbsent(time[0], new ArrayList<>());
            edges.get(time[0]).add(new int[]{time[1], time[2]});
        }

        // <[to1, cost1], 依照花費時間cost小到大
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); 
        pq.offer(new int[]{k, 0});
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int time = cur[1];
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            res = Math.max(res, time);

            if (edges.containsKey(node)) {
                for (int[] next : edges.get(node)) {
                    pq.offer(new int[]{next[0], next[1] + time});
                }
            }
        }

        return (visited.size() == n) ? res : -1;
    }
}


//Shortest Path Bellman-Ford Algorithm O(VE) O(V), E = times.size(), V = n
class Solution743_2 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dists = new int[n + 1]; // 可到達的最短距離, dists[0]不使用
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[k] = 0; // 起點
        for (int i = 0; i < n - 1; ++i) {
            for (int[] time : times) {
                int from = time[0];
                int to = time[1];
                int t = time[2]; // 如果有限制收縮次數, 要建立temp避免污染, 本題不用
                if (dists[from] != Integer.MAX_VALUE) { // Relaxation V-1次
                    dists[to] = Math.min(dists[to], dists[from] + t); // 更新為較小的
                }
            }
        }

        // 如果需要檢驗負環則在這裡, 再循環一次看是否能減少距離 (本題不用)
        // for (int[] time : times) { // 第V次判斷有無負環
        //     int from = time[0];
        //     int to = time[1];
        //     int t = time[2];
        //     if (dists[from] != Integer.MAX_VALUE && dists[to] > dists[from] + t) {
        //         //存在負環
        //     }
        // }

        // 找出dists最大值
        int res = 0;
        for (int i = 1; i < n + 1; ++i) { // 去掉index 0
            res = Math.max(res, dists[i]);
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }
}


//Shortest Path Bellman-Ford Algorithm (SPFA Algorithm) O(VE) O(V + E), E = times.size(), V = n, 進化版的Bellman-Ford
class Solution743_3 {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 建立edges
        Map<Integer, List<int[]>> edges = new HashMap<>(); // <from, <[to1, cost1], [to2, cost2], ...>>
        for (int[] time : times) {
            edges.putIfAbsent(time[0], new ArrayList<>());
            edges.get(time[0]).add(new int[]{time[1], time[2]});
        }


        int[] dists = new int[n + 1]; // 可到達的最短距離, dists[0]不使用
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[k] = 0; // 起點
        Queue<Integer> q = new ArrayDeque<>(); // 用來放之後要跑的點
        q.offer(k);
        boolean[] inQueue = new boolean[n + 1]; // 該點是否已經在queue裡面
        inQueue[k] = true;
        while (!q.isEmpty()) {
            int from = q.poll();
            inQueue[from] = false;

            if (dists[from] != Integer.MAX_VALUE && edges.containsKey(from)) {
                for (int[] next : edges.get(from)) {
                    int to = next[0];
                    int t = next[1];
                    if (dists[to] > dists[from] + t) {
                        dists[to] = dists[from] + t;
                        if (inQueue[to]) { // 避免重複入queue
                            continue;
                        }
                        q.offer(to);
                        inQueue[to] = true;
                    }
                }
            }
        }

        // 找出dists最大值
        int res = 0;
        for (int i = 1; i < n + 1; ++i) { // 去掉index 0
            res = Math.max(res, dists[i]);
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }
}