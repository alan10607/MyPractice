package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Tree {

    //Time Complexity: O(n), Space Complexity: O(h), n為節點個數, h為最大節點深度
    class Solution104 {
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

        public int maxDepth(TreeNode root) {
            //設定例外條件
            if(root == null) return 0;//到底部

            //每次DFS就 + 1
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }

        //Time Complexity: O(n), Space Complexity: O(n), n為節點個數, h為quene的儲存量, 最多為n
        public int maxDepth2(TreeNode root) {
            //試試看BFS, 設定例外
            if(root == null) return 0;

            int count = 0;
            Deque<TreeNode> quene = new LinkedList<TreeNode>();
            quene.offer(root);
            while(!quene.isEmpty()){
                count++;

                //開始跑層, 只需要跑目前層的數量
                int size = quene.size();
                for(int i=0; i<size; i++){
                    TreeNode temp = quene.poll();
                    if(temp.left != null)
                        quene.offer(temp.left);

                    if(temp.right != null)
                        quene.offer(temp.right);
                }
            }
            return count;
        }
    }

    //Time Complexity: O(min(m,n)), Space Complexity: O(min(m,n)), m,n為兩tree之節點數
    class Solution100 {
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

        public boolean isSameTree(TreeNode p, TreeNode q) {
            //設定例外返回點
            if(p == null && q == null) return true;//判斷到底正常, 回true
            if(p == null || q == null) return false;//只有一方為null
            if(p.val != q.val) return false;

            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

    }

    //Time Complexity: O(n), Space Complexity: O(n), n為tree之節點數
    class Solution226 {
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

        public TreeNode invertTree(TreeNode root) {
            //設定例外
            if(root == null) return null;

            //swap左右
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            //後續節點照做
            invertTree(root.left);
            invertTree(root.right);

            return root;//in-place回傳
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n), n為tree之節點數
    class Solution124 {
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

        public int res = 0;
        public int maxPathSum(TreeNode root) {
            res = root.val;//如果只有一個node, 至少會要回傳這個點(即使是負的)
            dfs(root);//回傳該node底下路線的最大值, 順便計算兩側路線的最大值(即res)
            return res;
        }

        public int dfs(TreeNode root){
            //設定返回點
            if(root == null) return 0;

            //計算兩側的大小, 若小於0則乾脆不走那個分支
            int leftMax = Math.max(dfs(root.left), 0);
            int rightMax = Math.max(dfs(root.right), 0);

            //在這裡更新可能的最大數
            int sum = root.val + leftMax + rightMax;//兩個分側的合
            res = Math.max(res, sum);

            return root.val + Math.max(leftMax, rightMax);//更新節點值
        }
        /* 從底部開始判斷相加
                1
              2   3
            -3 2 4 5

                1
            [4]   [8]  res=4, res=12
           -3 2   4 5

               [9]     res=13
            [4]   [8]
           -3 1   4 5
        */
    }

    //Time Complexity: O(n), Space Complexity: O(n), 其實就是BFS的複雜度
    class Solution102 {
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

        public List<List<Integer>> levelOrder(TreeNode root) {
            //要求遍歷回傳每一層, 其實就是在做BFS
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            //設定例外條件
            if(root == null) return res;

            Deque<TreeNode> quene = new LinkedList<TreeNode>();
            quene.offer(root);

            while(!quene.isEmpty()){
                List<Integer> level = new ArrayList<Integer>();//用來存這一層的node

                int size = quene.size();//這一層的數量
                for(int i=0; i<size; i++){
                    TreeNode temp = quene.poll();
                    level.add(temp.val);
                    if(temp.left != null) quene.offer(temp.left);
                    if(temp.right != null) quene.offer(temp.right);
                }

                res.add(level);
            }
            return res;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n), n為tree之節點數
    class Solution297 {
        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        public String serialize(TreeNode root) {
            //本提要實作tree序列化與反序列化
            //序列化方式: DFS收集node, 用","隔開, 若是null則給N
            StringBuffer res = new StringBuffer();
            seDFS(root, res);
            return res.substring(0, res.length() - 1);//記得去掉最後一個,
        }

        public void seDFS(TreeNode root, StringBuffer res){
            //設定返回條件, 若null插入N
            if(root == null){
                res.append("N,");
                return;
            }

            res.append(root.val).append(",");//加入此node

            //左右兩側接續DFS
            seDFS(root.left, res);
            seDFS(root.right, res);
            return;
        }

        public TreeNode deserialize(String data) {
            //先將String Array轉為LinkedList quene
            String[] datas = data.split(",");
            Deque<String> quene = new LinkedList<String>(Arrays.asList(datas));
            return deDFS(quene);
        }

        public TreeNode deDFS(Deque<String> quene){
            String val = quene.poll();
            //設定返回條件
            if("N".equals(val)) return null;

            //若不為null, 依照DFS順序建立新的TreeNode
            TreeNode root = new TreeNode(Integer.parseInt(val));//要轉為數字
            root.left = deDFS(quene);
            root.right = deDFS(quene);
            return root;
        }
    }

    //Time Complexity: O(st), Space Complexity: O(h), s為root節點數, t為subTree節點數, h為root深度即要記憶大樹的深度
    class Solution572 {
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

        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if(subRoot == null) return true;//設定例外, 任何tree都一定有null
            if(root == null) return false;//設定跳出點, 已經走到底部

            if(isSameTree(root, subRoot))
                return true;

            //root接續尋找可能
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        //透過DFS判定s, t是否為相同的tree
        public boolean isSameTree(TreeNode s, TreeNode t){
            //設定例外
            if(s == null && t == null) return true;
            if(s == null || t == null || s.val != t.val) return false;//其中一個null, 為另一個不為null

            //s.val == t.val, 繼續左右兩邊DFS
            return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n), n為tree之節點數
    class Solution105 {
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

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();//用來記錄inorder位置, 方便遞迴
            for(int i=0; i<inorder.length; i++)
                inMap.put(inorder[i], i);

            return build(preorder, inMap, 0, inorder.length - 1);
        }

        public int preIndex = 0;//一個巧妙的做法, 用來記錄preorder的起始位置(相當於pre的root)
        public TreeNode build(int[] pre, Map<Integer, Integer> inMap, int left, int right){
            //設定例外
            if(left > right) return null;

            int val = pre[preIndex++];//!!因為遍歷方式其實就是DFS preorder, 所以其實每次都+1就好了
            int inRoot = inMap.get(val);//應對的inorder位置
            TreeNode root = new TreeNode(val);
            root.left = build(pre, inMap, left, inRoot - 1);
            root.right = build(pre, inMap, inRoot + 1, right);
            return root;
        }

        //Time Complexity: O(n), Space Complexity: O(n), n為tree之節點數
        public TreeNode buildTree2(int[] preorder, int[] inorder) {
            Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();//用來記錄inorder位置, 方便遞迴
            for(int i=0; i<inorder.length; i++)
                inMap.put(inorder[i], i);

            return build(preorder, inMap, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        public TreeNode build(int[] pre, Map<Integer, Integer> inMap, int preStart, int preEnd, int inStart, int inEnd){
            //設定例外
            if(preStart > preEnd) return null;

            int val = pre[preStart];//取preorder第一個, 即root
            int inRoot = inMap.get(val);//應對的inorder位置
            int toLeft = inRoot - inStart;//inorder左邊的長度

            //開始建立
            TreeNode root = new TreeNode(val);
            root.left = build(pre, inMap, preStart + 1, preStart + toLeft, inStart, inRoot - 1);
            root.right = build(pre, inMap, preStart + toLeft + 1, preEnd, inRoot + 1, inEnd);

            return root;
        }
        /*
                1
            2       3
                  4   5

        preorder = [1, 2, 3, 4, 5]
        inorder  = [2, 1, 4, 3, 5]

        先取preorder[0](= 1), 拆開inorder, 依照inorder左右數量拆開preorder
        preorder = (1) [2] [3, 4, 5]
        inorder  = [2] (1) [4, 3, 5]

        取2, 3
        preorder = (2) (3) [4] [5]
        inorder  = (2) [4] (3) [5]

        取4, 5
        preorder = (4) (5)
        inorder  = (4) (5)

        DFS:
            Pre-order Traversal  前序遍歷 root -> left -> right
            In-order Traversal   中序遍歷 left -> root -> right
            Post-order Traversal 後序遍歷 left -> right-> root
        BFS
        */
    }


    //Time Complexity: O(n), Space Complexity: O(n), n為tree之節點數
    class Solution98 {
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

        public boolean isValidBST(TreeNode root) {
            //題目的root.val有可能給到Integer.MAX_VALUE, 直接改用Long
            return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);//-2^63 ~ 2^63-1
        }

        public boolean dfs(TreeNode root, long left, long right) {
            //設定例外
            if (root == null) return true;//驗證到底部了

            //如果在範圍內, 繼續遍歷左右分支
            if (left < root.val && root.val < right)
                return dfs(root.left, left, root.val) && dfs(root.right, root.val, right);

            return false;
        }
        /*  以下驗證會失敗, 因為4 < 5
                5
            3       7
                  4   8
        依照DFS驗證:
        MIN_VALUE < 5 < MAX_VALUE
        MIN_VALUE < 3 < 5
        5 < 7 < MAX_VALUE
        5 < 4 < 7 => return false

        */

        //Time Complexity: O(n), Space Complexity: O(n), n為tree之節點數
        public boolean isValidBST2(TreeNode root) {
            //透過In-order Traversal
            Deque<TreeNode> quene = new LinkedList<TreeNode>();
            long last = Long.MIN_VALUE;

            //開一個入口給子while, 第一次加入quene放在子while
            while(!quene.isEmpty() || root != null){
                while(root != null){
                    quene.push(root);//紀錄root
                    root = root.left;//往左下去到底
                }

                //開始掃描, 從底層拿出來
                root = quene.poll();
                if(last >= root.val)//現在的node應該要比前一個的大
                    return false;

                last = root.val;//更新到上一個的大小
                root = root.right;//掃描完root後往右
            }
            return true;
        }
        /* 如果是In-order Traversal, 則left -> root -> right一定會照大小排列
                2
            1       4
                  3   5
        inorder = [1, 2, 3, 4, 5]
        */
    }

}