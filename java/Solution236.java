package leetCode.java;

//DFS O(V) O(H)
class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if(left != null && left.val != p.val && left.val != q.val) return left;

        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(right != null && right.val != p.val && right.val != q.val) return right;

        if(left != null && right != null) return root;

        return left != null ? left : right;
    }
}