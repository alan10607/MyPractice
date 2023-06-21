package leetCode.java;

//DFS O(V) O(H)
class Solution124 {
    public int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root){
        if(root == null) return 0;

        int lSum = Math.max(dfs(root.left), 0);//若比0小則不取
        int rSum = Math.max(dfs(root.right), 0);
        res = Math.max(res, root.val + lSum + rSum);//自己+左右葉
        return root.val + Math.max(lSum, rSum);//自己+左或右葉
    }
}