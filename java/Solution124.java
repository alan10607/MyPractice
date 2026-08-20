package leetCode.java;

//DFS O(V) O(H)
class Solution124 {
    int res = Integer.MIN_VALUE; // non-empty path, 至少要選一個 node

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, dfs(root.left)); // 如果是負的不如不選
        int right = Math.max(0, dfs(root.right));
        res = Math.max(res, root.val + left + right); // 自己+左右葉
        return root.val + Math.max(left, right); // 自己+左或右葉
    }
}