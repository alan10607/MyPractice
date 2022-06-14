package leetCode.test2nd;

import java.util.*;

public class Blind75_Tree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //DFS
    class Solution104 {
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    //DFS
    class Solution100 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p == null && q == null) return true;//到底
            if(p == null || q == null) return false;
            if(p.val != q.val) return false;
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    //DFS
    class Solution226 {
        public TreeNode invertTree(TreeNode root) {
            if(root == null) return null;

            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            invertTree(root.left);
            invertTree(root.right);

            return root;
        }
    }

    //*DFS
    class Solution124 {
        public int max = Integer.MIN_VALUE;//設為最小, 避免全都是負的tree回傳0
        public int maxPathSum(TreeNode root) {
            dfs(root);
            return max;
        }

        public int dfs(TreeNode root) {
            if(root == null) return 0;

            int left = Math.max(dfs(root.left), 0);//若為負, 則捨棄
            int right = Math.max(dfs(root.right), 0);//若為負, 則捨棄
            max = Math.max(max, root.val + left + right);

            return root.val + Math.max(left, right);
        }
    }

    //*BFS
    class Solution102 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if(root == null) return res;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);//先放入第一個
            while(!queue.isEmpty()){
                List<Integer> level = new ArrayList<>();
                int size = queue.size();
                for(int i=0; i<size; i++){
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if(node.left != null) queue.offer(node.left);
                    if(node.right != null) queue.offer(node.right);
                }
                res.add(level);
            }
            return res;
        }
    }

    //DFS
    class Solution297 {
        class Codec {
            public String serialize(TreeNode root) {
                //依照pre-order思路建立
                return seDFS(root);
            }

            public String seDFS(TreeNode root) {
                if (root == null) return "N";
                return root.val + ";" + seDFS(root.left) + ";" + seDFS(root.right);
            }

            public TreeNode deserialize(String data) {
                String[] arr = data.split(";");
                Queue<String> queue = new LinkedList<>(Arrays.asList(arr));
                return deDFS(queue);
            }

            public TreeNode deDFS(Queue<String> queue) {
                String str = queue.poll();
                if ("N".equals(str)) return null;

                TreeNode node = new TreeNode(Integer.parseInt(str));
                node.left = deDFS(queue);
                node.right = deDFS(queue);
                return node;
            }
        }
    }

    //*DFS
    class Solution572 {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if(root == null) return false;

            //拆解問題為: 是否存在相同的兩個tree
            if(isSameTree(root, subRoot)){
                return true;
            }else{
                return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
            }
        }

        public boolean isSameTree(TreeNode a, TreeNode b){
            if(a == null && b == null) return true;
            if(a == null || b == null || a.val != b.val) return false;
            return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
        }
    }

    //*DFS
    class Solution105 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> inMap = new HashMap<>();//方便找查, 空間換時間
            for(int i=0; i<inorder.length; i++)
                inMap.put(inorder[i], i);

            return splitInorder(preorder, inMap, 0, inMap.size() - 1);
        }

        public int preIndex = 0;
        public TreeNode splitInorder(int[] preorder, Map<Integer, Integer> inMap, int start, int end){
            if(start > end) return null;//設定例外, 若相等則繼續消耗preIndex

            int val = preorder[preIndex++];
            int inIndex = inMap.get(val);

            TreeNode node = new TreeNode(val);
            node.left = splitInorder(preorder, inMap, start, inIndex - 1);
            node.right = splitInorder(preorder, inMap, inIndex + 1, end);
            return node;
        }
    }

    //*DFS(in-order)
    class Solution98 {
        public boolean isValidBST(TreeNode root) {
            //in-order
            Deque<TreeNode> queue = new LinkedList<>();
            long last = Long.MIN_VALUE;
            while(root != null || !queue.isEmpty()){
                while(root != null){
                    queue.push(root);//in-order是用push, BFS則是用offer
                    root = root.left;
                }

                root = queue.poll();
                if(last >= root.val) return false;
                last = root.val;

                root = root.right;//往右, 如果為null, 會繼續poll()上一層
            }
            return true;
        }
    }

    //*DFS(in-order)
    class Solution230 {
        public int kthSmallest(TreeNode root, int k) {
            //本題的重點是本身就是BST, 不用再用一次PriorityQueue了
            //BST的排列依照in-order
            Deque<TreeNode> queue = new LinkedList<>();
            while(root != null || !queue.isEmpty()){
                while(root != null){
                    queue.push(root);
                    root = root.left;
                }

                root = queue.poll();
                if(--k == 0) return root.val;

                root = root.right;
            }
            return -1;
        }
    }

    //DFS
    class Solution235 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(q.val < root.val) return lowestCommonAncestor(root.left, p, q);
            if(root.val < p.val) return lowestCommonAncestor(root.right, p, q);
            return root;
        }
    }

    //Trie
    class Solution208 {
        class Trie {
            public Trie[] children;
            public boolean endFlag;

            public Trie() {
                children = new Trie[26];
                endFlag = false;
            }

            public void insert(String word) {
                Trie root = this;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 'a';
                    if (root.children[index] == null)
                        root.children[index] = new Trie();//用實體化當作確認

                    root = root.children[index];
                }
                root.endFlag = true;
            }

            public boolean search(String word) {
                Trie root = this;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 'a';
                    if (root.children[index] == null) return false;
                    root = root.children[index];
                }
                return root.endFlag;
            }

            public boolean startsWith(String prefix) {
                Trie root = this;
                for (int i = 0; i < prefix.length(); i++) {
                    int index = prefix.charAt(i) - 'a';
                    if (root.children[index] == null) return false;
                    root = root.children[index];
                }
                return true;
            }
        }
    }

    //Trie
    class Solution211 {
        class WordDictionary {
            public WordDictionary[] children;
            public boolean endFlag;

            public WordDictionary() {
                children = new WordDictionary[26];
                endFlag = false;
            }

            public void addWord(String word) {
                WordDictionary root = this;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 'a';
                    if (root.children[index] == null)
                        root.children[index] = new WordDictionary();
                    root = root.children[index];
                }
                root.endFlag = true;
            }

            public boolean search(String word) {
                return search(word, this);
            }

            public boolean search(String word, WordDictionary root) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == '.') {
                        for (WordDictionary child : root.children) {
                            //記得要判斷null
                            if (child != null && search(word.substring(i + 1), child))
                                return true;
                        }
                        return false;
                    }

                    int index = word.charAt(i) - 'a';
                    if (root.children[index] == null) return false;
                    root = root.children[index];
                }
                return root.endFlag;
            }
        }
    }

    //*DFS + Trie
    class Solution212 {
        public List<String> findWords(char[][] board, String[] words) {
            Set<String> res = new HashSet<>();
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];
            Trie trie = new Trie();
            for(String word : words)
                trie.insert(word);

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    dfs(i, j, trie, board, visited, res);
                }
            }

            List<String> resList = new ArrayList<>(res);
            return resList;
        }

        public void dfs(int i, int j, Trie trie, char[][] board, boolean[][] visited, Set<String> res){
            int index = board[i][j] - 'a';
            if(visited[i][j] || trie.children[index] == null) return;

            visited[i][j] = true;
            trie = trie.children[index];//要先移動trie, 下面的判斷才會正確

            if(trie.endFlag) res.add(trie.word);

            int m = board.length;
            int n = board[0].length;

            if(i + 1 <  m) dfs(i + 1, j, trie, board, visited, res);
            if(i - 1 >= 0) dfs(i - 1, j, trie, board, visited, res);
            if(j + 1 <  n) dfs(i, j + 1, trie, board, visited, res);
            if(j - 1 >= 0) dfs(i, j - 1, trie, board, visited, res);

            visited[i][j] = false;
        }

        class Trie {
            public Trie[] children;
            public boolean endFlag;
            public String word;

            public Trie(){
                children = new Trie[26];
                endFlag = false;
            }

            public void insert(String word){
                Trie root = this;
                for(int i=0; i<word.length(); i++){
                    int index = word.charAt(i) - 'a';
                    if(root.children[index] == null)
                        root.children[index] = new Trie();

                    root = root.children[index];
                }
                root.endFlag = true;
                root.word = word;
            }
        }
    }

}