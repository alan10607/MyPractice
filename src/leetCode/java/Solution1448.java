package leetCode.java;

//DFS O(V) O(H)
class Solution1448 {
    public int goodNodes(TreeNode root) {
        return dfs(Integer.MIN_VALUE, root);
    }

    public int dfs(int max, TreeNode root){
        if(root == null) return 0;

        //named good if there are no nodes greater than this
        int good = 0;
        if(root.val >= max){//等於也算good
            good++;
            max = root.val;
        }
        good += dfs(max, root.left);
        good += dfs(max, root.right);
        return good;
    }
}