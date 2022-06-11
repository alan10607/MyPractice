package leetCode.test2nd;

import java.util.*;

public class Blind75_Graph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    //*DFS(DAG)
    class Solution55 {
        public Map<Node, Node> visited = new HashMap<>();

        public Node cloneGraph(Node node) {
            if(node == null) return null;
            if(visited.containsKey(node)) return visited.get(node);//回傳clone node用以add to clone.neighbors

            Node clone = new Node(node.val);
            visited.put(node, clone);//記得標記走訪過

            for(Node neighbor : node.neighbors){
                clone.neighbors.add(cloneGraph(neighbor));
            }

            return clone;
        }
    }

    //*Topological sorting
    class Solution207 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //1 edges
            Map<Integer, List<Integer>> edges = new HashMap<>();
            int[] counts = new int[numCourses];
            for(int[] prerequisite : prerequisites){
                int course = prerequisite[0];
                int pre = prerequisite[1];
                if(!edges.containsKey(course)) edges.put(course, new ArrayList<>());
                edges.get(course).add(pre);

                counts[pre]++;
            }

            //2 find start
            Deque<Integer> queue = new LinkedList<>();
            for(int i=0; i<counts.length; i++){
                if(counts[i] == 0)
                    queue.offer(i);//記得是加入課程
            }

            //3 run
            int walkCount = 0;
            while(!queue.isEmpty()){
                int parent = queue.poll();
                walkCount++;

                if(edges.containsKey(parent)){
                    List<Integer> childs = edges.get(parent);
                    for(int child : childs){//會自動拆封
                        counts[child]--;
                        if(counts[child] == 0)
                            queue.offer(child);
                    }
                }
            }

            return walkCount == numCourses;
        }
    }

    //DFS
    class Solution417 {
        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> res = new ArrayList<>();
            int m = heights.length;
            int n = heights[0].length;
            boolean[][] pFlag = new boolean[m][n];
            boolean[][] aFlag = new boolean[m][n];

            for(int i=0; i<m; i++)
                dfs(i, 0, pFlag, heights);

            for(int j=0; j<n; j++)
                dfs(0, j, pFlag, heights);

            for(int i=0; i<m; i++)
                dfs(i, n - 1, aFlag, heights);

            for(int j=0; j<n; j++)
                dfs(m - 1, j, aFlag, heights);

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(pFlag[i][j] && aFlag[i][j]){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(i);
                        temp.add(j);
                        res.add(temp);
                    }
                }
            }
            return res;
        }

        public void dfs(int i, int j, boolean[][] flag, int[][] heights){
            if(flag[i][j]) return;//已檢查過可流入

            flag[i][j] = true;//可流入

            int m = heights.length;
            int n = heights[0].length;
            //高度一樣也可流入
            if(i + 1 <  m && heights[i + 1][j] >= heights[i][j]) dfs(i + 1, j, flag, heights);
            if(i - 1 >= 0 && heights[i - 1][j] >= heights[i][j]) dfs(i - 1, j, flag, heights);
            if(j + 1 <  n && heights[i][j + 1] >= heights[i][j]) dfs(i, j + 1, flag, heights);
            if(j - 1 >= 0 && heights[i][j - 1] >= heights[i][j]) dfs(i, j - 1, flag, heights);

            return;
        }
    }

    //DFS
    class Solution200 {
        public int numIslands(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int count = 0;

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(grid[i][j] == '1'){
                        count++;
                        dfs(grid, i, j);//相鄰的陸地變成'*'
                    }
                }
            }
            return count;
        }

        public void dfs(char[][] grid, int i, int j){
            if(grid[i][j] != '1') return;

            grid[i][j] = '*';

            int m = grid.length;
            int n = grid[0].length;
            if(i + 1 <  m) dfs(grid, i + 1, j);
            if(i - 1 >= 0) dfs(grid, i - 1, j);
            if(j + 1 <  n) dfs(grid, i, j + 1);
            if(j - 1 >= 0) dfs(grid, i, j - 1);

            return;
        }
    }

    //*HashMap
    class Solution128 {
        public int longestConsecutive(int[] nums) {
            int max = 0;
            Set<Integer> memo = new HashSet<>();
            for(int num : nums)
                memo.add(num);

            for(int num : nums){
                if(!memo.contains(num - 1)){
                    int len = 0;
                    int temp = num;//要記得拿一個暫代num
                    while(memo.contains(temp)){
                        len++;
                        temp++;
                    }
                    max = Math.max(max, len);
                }
            }
            return max;
        }
    }

}