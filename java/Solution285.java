package leetCode.java;

//DFS(BST) O(logV) O(H)
class Solution285 {//lintcode448
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;

        if(root.val <= p.val){//等於也由此跳過,往右找
            return inorderSuccessor(root.right, p);
        }else{
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null ? root : left;
        }
    }
}