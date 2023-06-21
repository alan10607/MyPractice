package leetCode.java;

//DFS(BST) O(logV) O(1)
class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //binary search tree (BST), find the lowest common ancestor (LCA) 最小共同祖先
        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{// p.val < root.val && root.val < q.val
            return root;
        }
    }
}