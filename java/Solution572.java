package leetCode.java;

//DFS O(VU) O(H), U = root節點數, V = subRoot節點數, H = subRoot高度
class Solution572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == null && b == null;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }
}