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

    //*DFS
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
                //本題不論哪個方向都ok, 但要注意counts要設對
                int course = prerequisite[0];
                int pre = prerequisite[1];
                if(!edges.containsKey(course)) edges.put(course, new ArrayList<>());
                edges.get(course).add(pre);

                counts[pre]++;
            }

            //2 find start
            Queue<Integer> queue = new LinkedList<>();
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

    //*Topological sort + PriorityQueue
    class Solution269 {
        public String alienOrder(String[] words) {
            //1 edges & counts
            //先找出所有字母, 避免漏掉, ["zy","zx"] => "yxz"
            Map<Character, Integer> counts = new HashMap<>();
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    if (!counts.containsKey(c)) counts.put(c, 0);
                }
            }

            Map<Character, List<Character>> edges = new HashMap<>();
            for (int i = 1; i < words.length; i++) {
                String word1 = words[i - 1];
                String word2 = words[i];
                //The dictionary is invalid, if string a is prefix of string b and b is appear before a.
                if (word1.startsWith(word2))
                    return "";

                for (int j = 0; j < word1.length() && j < word2.length(); j++) {
                    char c1 = word1.charAt(j);
                    char c2 = word2.charAt(j);
                    if (c1 != c2) {
                        if (!edges.containsKey(c1)) edges.put(c1, new ArrayList<>());
                        edges.get(c1).add(c2);
                        counts.put(c2, counts.get(c2) + 1);
                        break;
                    }
                }
            }

            //2 find start
            //The letters in one string are of the same rank by default and are sorted in Human dictionary order.
            PriorityQueue<Character> queue = new PriorityQueue<>();//若都為parent依照預設字母排列, 所以用PriorityQueue
            for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
                if (entry.getValue() == 0)
                    queue.offer(entry.getKey());
            }

            //3 run
            StringBuffer res = new StringBuffer();
            while (!queue.isEmpty()) {
                char parent = queue.poll();
                res.append(parent);

                if (edges.containsKey(parent)) {
                    List<Character> childs = edges.get(parent);
                    for (char child : childs) {
                        counts.put(child, counts.get(child) - 1);
                        if (counts.get(child) == 0)
                            queue.offer(child);
                    }
                }
            }

            return res.length() == counts.size() ? res.toString() : "";
        }
    }

    //*Union-find
    class Solution261 {
        public boolean validTree(int n, int[][] edges) {
            //tree的edge = vertex - 1, 避免有單獨的點出現
            if(edges.length != n - 1) return false;

            int[] parents = new int[n];//用來代表父節點是哪一個
            Arrays.fill(parents, -1);

            for(int[] edge : edges){
                int a = find(edge[0], parents);
                int b = find(edge[1], parents);

                if(a == b) return false;

                parents[a] = b;
            }
            return true;
        }

        //尋找父節點
        public int find(int node, int[] parents){
            if(parents[node] == -1) return node;
            return find(parents[node], parents);
        }
        /*
        edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
        0-1-4       0-1
        |-2     ->  |-2
        |-3         |-3
                    |-4

        edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
        0-1-2   ->  0-1
        | | |       |-2
        4 --3       |-3
                    |-3  ==>有重複出現
                    |-4
        */
    }

    //*Union-find
    public class Solution323 {
        public int countComponents(int n, int[][] edges) {
            int count = n;//獨立的node數量
            int parents[] = new int[n];
            Arrays.fill(parents, -1);

            for(int[] edge : edges){
                int a = find(edge[0], parents);
                int b = find(edge[1], parents);

                //若無成環, 才連接, 否則已經在同一個union
                if(a != b) {
                    parents[a] = b;
                    count--;//減少獨立的node數量
                }

            }
            return count;
        }

        public int find(int node, int[] parents){
            if(parents[node] == -1) return node;
            return find(parents[node], parents);
        }
    }
}