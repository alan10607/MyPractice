package leetCode.java;

//DFS O(V) O(H), H為tree深度, 最多為V
class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}