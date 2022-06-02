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
    public class Solution297 {
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
            //先將String Array轉為LindedList quene
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

}