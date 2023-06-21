package leetCode.java;

//DFS O(V) O(H)
class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return check(root.left, root.right);
    }

    public boolean check(TreeNode a, TreeNode b){
        if(a == null || b == null) return a == null && b == null;
        if(a.val != b.val) return false;
        return check(a.left, b.right) && check(a.right, b.left);
    }
}