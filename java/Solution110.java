package leetCode.java;

//DFS O(V) O(H)
class Solution110 {
    public boolean isBalanced(TreeNode root) {
        int height = dfs(root);
        return height != -1;
    }

    public int dfs(TreeNode root){
        if(root == null) return 0;

        int lHeight = dfs(root.left);
        int rHeight = dfs(root.right);
        //height differ more than 1
        if(lHeight == -1 || rHeight == -1 || Math.abs(lHeight - rHeight) > 1)
            return -1;//表示false

        return Math.max(lHeight, rHeight) + 1;
    }
}