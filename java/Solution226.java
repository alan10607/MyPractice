package leetCode.java;

//DFS O(V) O(H), H為tree深度, 最多為V
class Solution226 {
    public TreeNode invertTree(TreeNode root) { // 透過遍歷思維
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}


class Solution226_2 {
    public TreeNode invertTree(TreeNode root) { // 另一個解法, 透過分解思路
        if (root == null) {
            return root;
        }
        
        TreeNode left = invertTree(root.left); // 不能直接root.left=invertTree(root.right), 會被覆蓋
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}