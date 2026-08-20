package leetCode.java;

//DFS(BST) O(logV) O(1)
class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Binary search tree (BST), find the lowest common ancestor (LCA) 最小共同祖先
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else { // p.val<root.val<q.val or q.val<root.val<p.val
            return root;
        }
    }
}