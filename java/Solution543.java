package leetCode.java;

//DFS O(V) O(H)
class Solution543 {
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res, left + right); // 題目所求是edge邊數, 不是node數
        return Math.max(left, right) + 1;
    }
}