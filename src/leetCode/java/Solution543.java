package leetCode.java;

//DFS O(V) O(H)
class Solution543 {
    public int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root){
        if(root == null) return 0;

        int l = dfs(root.left);
        int r = dfs(root.right);
        res = Math.max(res, l + r);
        return Math.max(l, r) + 1;
    }
}