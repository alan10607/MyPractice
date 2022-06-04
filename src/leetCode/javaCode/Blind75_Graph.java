package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Graph {

    //Time Complexity: O(n), Space Complexity: O(n), n為所有節點數量, 同時空間複雜度為深度也是節點數
    class Solution133 {
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

        public Map<Node, Node> visited = new HashMap<Node, Node>();//紀錄已複製的node, <k, v> = <舊node, 新node>

        public Node cloneGraph(Node node) {
            //如果是null, 不用複製直接回傳
            if(node == null)
                return null;

            //若已經複製, 直接回傳
            if(visited.containsKey(node))
                return visited.get(node);


            Node clone = new Node(node.val);
            visited.put(node, clone);//記錄已複製的node

            //複製clone後設定該neighbors
            for(Node neighbor : node.neighbors){
                clone.neighbors.add(cloneGraph(neighbor));//把舊的鄰居node拿去dfs遍歷
            }

            return clone;
        }
    }

    //Time Complexity: O(V + E), Space Complexity: O(V + E), V為課程數numCourses, E為先修課程的要求數(edge), 其實DFS就是複雜度
    class Solution207 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //其實就是判斷graph有沒有環
            //numCourses為所有課程數, 課程編號為連續數列
            //先將課程轉成List = [[需修課1的可修課...], [需修課2的可修課...]...]
            List<List<Integer>> edge = new ArrayList<List<Integer>>();
            int[] flags = new int[numCourses];//設定遍歷的狀態

            for(int i=0; i<numCourses; i++)
                edge.add(new ArrayList<Integer>());//初始化

            for(int[] course : prerequisites)
                edge.get(course[1]).add(course[0]);//要修某課才能修的課放在一堆

            for(int i=0; i<flags.length; i++){
                if(!dfs(edge, flags, i))
                    return false;
            }

            return true;
        }

        public boolean dfs(List<List<Integer>> edge, int[] flags, int i) {
            //設定例外條件
            if(flags[i] == 1) return false;//遇到正在遍歷代表行程迴圈, 結束DFS
            if(flags[i] == -1) return true;//遇到已遍歷代表走到底了, 退出DFS

            flags[i] = 1;//設定為正在遍歷

            //繼續往下找節點
            for(int next : edge.get(i)){
                if(!dfs(edge, flags, next))
                    return false;//DFS有循環產生, 結束DFS
            }

            flags[i] = -1;//設定為已遍歷

            return true;
        }

        /* DFS
        prerequisites = [[0, 1], [0, 2], [1, 3], [1, 4], [3, 4]]
        edge = [[], [0], [0], [1], [1, 3]]

        0-->1-->3
        |	|	v
        |	--->4
        --->2

        */
    }

    //Time Complexity: O(mn), Space Complexity: O(mn)
    class Solution417 {
        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;
            //建立2個一樣大小的布林值表
            boolean[][] pacific = new boolean[m][n];
            boolean[][] atlantic = new boolean[m][n];
            List<List<Integer>> res = new ArrayList<List<Integer>>();

            //從邊線低窪開始DFS
            for(int i=0; i<m; i++)
                dfs(i, 0, pacific, heights);
            for(int i=0; i<n; i++)
                dfs(0, i, pacific, heights);
            for(int i=0; i<m; i++)
                dfs(i, n - 1, atlantic, heights);
            for(int i=0; i<n; i++)
                dfs(m - 1, i, atlantic, heights);

            //找出兩個海洋都是true的
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(pacific[i][j] && atlantic[i][j]){
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(i);
                        temp.add(j);
                        res.add(temp);
                    }
                }
            }
            return res;
        }

        //判斷是否可流入該海
        public void dfs(int i, int j, boolean[][] ocean, int[][] heights) {
            //設定例外, 若已判定可流入, 則表示已走過該節點
            if(ocean[i][j]) return;

            ocean[i][j] = true;//邊界預設可流入

            //上下左右四個方向找出比較高的跑dfs
            int m = heights.length;
            int n = heights[0].length;

            //檢查上下左右邊界且下一個大於等於此高度的
            if(i + 1 < m  && heights[i + 1][j] >= heights[i][j]) dfs(i + 1, j, ocean, heights);
            if(i - 1 >= 0 && heights[i - 1][j] >= heights[i][j]) dfs(i - 1, j, ocean, heights);
            if(j + 1 < n  && heights[i][j + 1] >= heights[i][j]) dfs(i, j + 1, ocean, heights);
            if(j - 1 >= 0 && heights[i][j - 1] >= heights[i][j]) dfs(i, j - 1, ocean, heights);
        }

        /* 能流入p與a的是上下或左右其中一個為最大值的

                    n
            p	p	p	p	p	p	p
            p	1	2	2	3  [5]	a
        m	p	3	2	3  [4] [4]	a
            p	2	4  [5]	3	1	a
            p  [6] [7]	1	4	5	a
            p  [5]	1	1	2	4	a
            a	a	a	a	a	a	a
        */
    }

    //Time Complexity: O(mn), Space Complexity: O(mn)
    class Solution200 {
        public int numIslands(char[][] grid) {
            //透過DFS找所有的陸地
            int m = grid.length;
            int n = grid[0].length;
            int islandCount = 0;

            //直接用題目給的grid當作flag, 如果遇到就改成0(變成海洋)
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(grid[i][j] == '1'){
                        islandCount++;
                        dfs(grid, i, j);
                    }
                }
            }

            return islandCount;
        }

        public void dfs(char[][] grid, int i, int j) {
            grid[i][j] = 'x';//表示已計算過

            int m = grid.length;
            int n = grid[0].length;

            //遍歷所有相鄰的陸地格grid[i][j] == 1, 記得設定收斂條件
            if(i + 1 < m  && grid[i + 1][j] == '1') dfs(grid, i + 1, j);
            if(i - 1 >= 0 && grid[i - 1][j] == '1') dfs(grid, i - 1, j);
            if(j + 1 < n  && grid[i][j + 1] == '1') dfs(grid, i, j + 1);
            if(j - 1 >= 0 && grid[i][j - 1] == '1') dfs(grid, i, j - 1);
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n)
    class Solution {
        public int longestConsecutive(int[] nums) {
            //使用Set記憶
            //設定例外條件, 要回傳的是連續數列的數量
            if(nums.length <= 1) return nums.length;

            //只要先記到Set, 就可以用contains回頭查是否存在
            Set<Integer> remember = new HashSet<Integer>();
            int max = 0;
            for(int num : nums)
                remember.add(num);

            for(int num : nums){
                //沒有比這個小一的才計算, 因為希望排序都從最小開始, 否則也只是重工
                if(!remember.contains(num - 1)){
                    int count = 1;//要從自己開始, 因為自己也算
                    int temp = num;
                    while(remember.contains(++temp)){//從這裡查看有沒有大一個的
                        count++;
                    }
                    max = Math.max(max, count);
                }
            }

            return max;
        }
    }

    //Time Complexity: O(V + E), Space Complexity: O(V), V為出現字母數(最大26), E = words.length
    class Solution269 {
        public String alienOrder(String[] words) {
            //要使用拓撲排序Topological sorting, 該sorting限制為圖不能有迴圈
            Map<Character, List<Character>> edge = new HashMap<Character, List<Character>>();//<父, [子...]>
            Set<Character> letters = new HashSet<Character>();
            int[] count = new int[26];//用來紀錄子節點出現次數, 為0則代表是最父的點
            PriorityQueue<Character> queue = new PriorityQueue<Character>();
            StringBuffer res = new StringBuffer();

            //1. 先找出有出現的所有的字母Set, 即所有node
            for(String word : words){
                for(char ch : word.toCharArray()){
                    letters.add(ch);
                }
            }

            //2. 先建立關聯edge
            for(int i=1; i<words.length; i++){
                String word1 = words[i - 1];
                String word2 = words[i];

                //題目規定, 檢查若word2是word1的前綴, 則退出給空白
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }
                for(int j=0; j<word1.length() && j<word2.length(); j++){
                    //將第一個遇到的不同字母放入關聯list
                    char parent = word1.charAt(j);
                    char child = word2.charAt(j);
                    if(parent != child){
                        if(!edge.containsKey(parent))
                            edge.put(parent, new ArrayList<Character>());

                        edge.get(parent).add(child);//ex. a > b, a > c, map格式<a, [b, c]> = <父, [子...]>

                        count[child - 'a']++;//紀錄子節點出現次數
                        break;
                    }
                }
            }

            //3. 從parent node開始遍歷
            for(Character letter : letters){
                //如果子節點出現次數為0, 代表是父節點
                if(count[letter - 'a'] == 0)
                    queue.offer(letter);
            }

            //開始排序, 並找出下一個父節點
            while(!queue.isEmpty()){
                Character parent = queue.poll();//要注意是包裝後的Character
                res.append(parent);//加到輸出字串

                List<Character> childs = edge.get(parent);
                //有可能這個節點沒有子節點(就是最末端)
                if(childs != null){
                    for(Character child : childs){
                        count[child - 'a']--;
                        if(count[child - 'a'] == 0)
                            queue.offer(child);//若減到為0, 則它就是下一個父節點
                    }
                }
            }

            //只有字母數一樣, 排列才是真的完成, Set用size
            return res.length() == letters.size()? res.toString() : "";
        }

        /*
        words = ["wrt", "wrf", "er", "ett", "rftt"] 比較第一個不同的字母, 兩兩比對代表了他們的順序
        "wrt", "wrf"  => t > f
        "wrf", "er"   => w > e
        "er", "ett"   => r > t
        "ett", "rftt" => e > r, result = wertf

        如果發生edge重複的情況, 該怎麼處理? 可以透過count計算若=0則加入PriorityQueue
        words = ["a", "ba", "bc", "c"]
                             count = [0, 1, 2], PriorityQueue = ['a'], sb = ""
        "a", "ba"  => a > b, count = [0, 0, 2], PriorityQueue = ['b'], sb = "a"
        "ba", "bc" => a > c, count = [0, 0, 1], PriorityQueue = ['b'], sb = "a"
        "bc", "c"  => b > c, count = [0, 0, 0], PriorityQueue = ['c'], sb = "ab"
        result = abc
        */
    }

    //Time Complexity: O(V + E), Space Complexity: O(V + E), V = n 即節點數, E = edges.length 即需要走到的下一個node數量
    public class Solution261 {
        public boolean validTree(int n, int[][] edges) {
            //本題是無向圖, 要驗證是否為樹的話, 就是都有連接且沒有產生環
            Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
            boolean[] visited = new boolean[n];

            //先建立雙向的關係
            for(int[] edge : edges){
                if(!graph.containsKey(edge[0]))
                    graph.put(edge[0], new ArrayList<Integer>());
                graph.get(edge[0]).add(edge[1]);

                if(!graph.containsKey(edge[1]))
                    graph.put(edge[1], new ArrayList<Integer>());
                graph.get(edge[1]).add(edge[0]);
            }

            //遍歷所有節點, 如果有環就回false
            if(!dfs(0, -1, graph, visited))//預設第一個節點的父節點為 -1
                return false;

            //確認遍歷的數量跟所有節點的量是一樣的, 即全部都有遍歷到
            for(boolean flag : visited){
                if(!flag)
                    return false;
            }

            return true;
        }

        public boolean dfs(int cur, int parent, Map<Integer, List<Integer>> graph, boolean[] visited) {
            //設定例外條件
            if(visited[cur]) return false;//表示已經遍歷過了, 產生了環

            visited[cur] = true;//設為已經經過

            //要判斷這個節點有沒有edge, 但如果一開始graph初始化都加上空的ArrayList就不用判斷這個
            if(graph.containsKey(cur)){
                for(Integer next : graph.get(cur)){
                    //因為是雙向圖, 要避免回到上一個行成loop
                    if(next != parent && !dfs(next, cur, graph, visited))
                        return false;
                }
            }

            return true;
        }

        /*  n = 5
		edges = [[0,1], [0,2], [0,3], [1,4]]

				0
				|
			1---2---3
			|
			4
	    */

        //Time Complexity: O(n logE), Space Complexity: O(n), n即n節點數, E = edges.length, logE因為每次探索最多可能跑n-1次
        public boolean validTree2(int n, int[][] edges) {
            //使用union find
            //首先可以判斷, 如果為tree, |E| = |V| - 1, 邊 = 點 - 1,  也就是要驗證 edges.length == n - 1
            if(edges.length != n - 1) return false;

            int[] parent = new int[n];
            Arrays.fill(parent, -1);//先把每個點的父節點設為 -1, 表示未設定

            for(int[] edge : edges){
                //分成兩端下去找父節點
                int a = find(edge[0], parent);
                int b = find(edge[1], parent);

                if(a == b) return false;//表示成圈

                parent[a] = b;//隨意設定成團
            }

            return true;
        }

        public int find(int node, int[] parent){
            if(parent[node] == -1) return node;//沒有父節點, 表示到頂, 回傳自己

            return find(parent[node], parent);//找一下更父的點
        }

        /* 使用Union Find
        n = 5, edges = [[0,1], [0,2], [0,3], [1,4]]

                 parent = [-1, -1, -1, -1, -1]
        [0,1] => parent = [1, -1, -1, -1, -1]
        [0,2] => parent = [1, 2, -1, -1, -1]
        [0,3] => parent = [2, 2, 3, -1, -1]
        [1,4] => parent = [2, 3, 3, 4, 1]

        */
    }

    //Time Complexity: O(V + E), Space Complexity: O(V + E), V = n, E = edges.length
    public class Solution323 {
        public int countComponents(int n, int[][] edges) {
            //用DFS
            Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
            boolean[] visited = new boolean[n];
            int count = 0;

            //這次試試看初始化graph給ArrayList
            for(int i=0; i<n; i++)
                graph.put(i, new ArrayList<Integer>());

            //既然是無方向圖, 就要建立雙向鍊錶
            for(int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);//<父, [子...]>
                graph.get(edge[1]).add(edge[0]);//<父, [子...]>
            }

            for(int i=0; i<n; i++){
                if(!visited[i]){//沒被訪問才遍歷
                    count++;
                    dfs(graph, visited, i);
                }
            }

            return count;
        }

        public void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int cur) {
            visited[cur] = true;//設為已訪問

            for(Integer next : graph.get(cur)){
                if(!visited[next])//設定例外條件
                    dfs(graph, visited, next);
            }
        }

        //Time Complexity: O(n logE), Space Complexity: O(n), n即n節點數, E = edges.length, logE因為每次探索最多可能跑n-1次
        public int countComponents2(int n, int[][] edges) {
            int count = n;//假設每個node都是獨立的, 最多可以有n
            int[] parent = new int[n];
            Arrays.fill(parent, -1);//初始化未訪問設為-1

            for(int[] edge : edges){
                //分成兩端下去找父節點
                int a = find(edge[0], parent);
                int b = find(edge[1], parent);

                if(a != b){//若 a == b, 則代表這個edge造成環, 還是同一個所以count不減1
                    parent[a] = b;//把兩個點union一起
                    count--;//兩個合一起就相當於數量-1
                }
            }

            return count;
        }

        public int find(int node, int[] parent){
            if(parent[node] == -1) return node;
            return find(parent[node], parent);
        }
    }

}