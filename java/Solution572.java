package leetCode.java;

//DFS O(VU) O(H), U = root節點數, V = subRoot節點數, H = subRoot高度
class Solution572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null) return true;
        if(root == null) return false;
        if(!isSametree(root,subRoot))
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

        return true;
    }

    public boolean isSametree(TreeNode a, TreeNode b) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        if(a.val != b.val) return false;
        return isSametree(a.left, b.left) && isSametree(a.right, b.right);
    }
}