package leetCode.javaCode;

import java.util.*;

/**
 *  Tree
 */
public class NeetCode150_Tree {
    public class TreeNode {
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

    //Time Complexity: O(n), Space Complexity: O(h), n為node個數, h為tree高度
    //dfs
    class Solution543 {
        public int res = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            //diameter直徑
            dfs(root);
            return res;
        }

        public int dfs(TreeNode root){
            if(root == null) return 0;
            int lLen = dfs(root.left);
            int rLen = dfs(root.right);
            res = Math.max(res, (lLen + rLen));
            return Math.max(lLen, rLen) + 1;//在這裡才 + 1, 加上自己
        }
    }

    //Time Complexity: O(n), Space Complexity: O(h), n為node個數, h為tree高度
    //DFS
    class Solution110 {
        public boolean isBalanced(TreeNode root) {
            return dfs(root) != -1;
        }

        public int dfs(TreeNode root){
            if(root == null) return 0;

            //the left and right subtrees of every node differ in [height] by no more than 1
            int lHeight = dfs(root.left);
            int rHeight = dfs(root.right);
            if(lHeight == -1 || rHeight == -1 || Math.abs(lHeight - rHeight) > 1) return -1;//設-1為失敗

            return Math.max(lHeight, rHeight) + 1;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n)
    //BFS
    class Solution199 {
        public List<Integer> rightSideView(TreeNode root) {
            //右視圖, 除了最右側的一條路以外也要算
            List<Integer> res = new ArrayList<>();
            if(root == null) return res;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()){
                int size = queue.size();
                int rightNum = -1;
                for(int i=0; i<size; i++){
                    TreeNode node = queue.poll();
                    rightNum = node.val;//update rightNum
                    if(node.left != null) queue.offer(node.left);
                    if(node.right != null) queue.offer(node.right);
                }
                res.add(rightNum);
            }

            return res;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n)
    //DFS
    class Solution1448 {
        public int res = 0;

        public int goodNodes(TreeNode root) {
            dfs(root, Integer.MIN_VALUE);
            return res;
        }

        public void dfs(TreeNode root, int max){
            if(root == null) return;

            if(root.val >= max){
                res++;
                max = root.val;
            }
            dfs(root.left, max);
            dfs(root.right, max);
        }
    }

}