package leetCode.java;

//DFS O(V) O(H)
class Solution110 {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) { //height differ more than 1
            return -1; // 表示不是 Balanced Binary Tree
        }
        return Math.max(left, right) + 1;
    }
}