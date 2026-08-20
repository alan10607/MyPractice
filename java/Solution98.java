package leetCode.java;

import java.util.*;

//DFS(BST) O(V) O(H)
class Solution98 {
    public boolean isValidBST(TreeNode root) {
         // 用null避免邊界問題, 如果用數字判斷就要用long, 因為node.val可能是MAX_VALUE or MIN_VALUE
        return dfs(root, null, null);
    }

    public boolean dfs(TreeNode root, TreeNode minNode, TreeNode maxNode) {
        if (root == null) {
            return true;
        }
        if (minNode != null && minNode.val >= root.val) { // min or max null 代表沒限制
            return false;
        }
        if (maxNode != null && maxNode.val <= root.val) {
            return false;
        }
        return dfs(root.left, minNode, root) && dfs(root.right, root, maxNode);
    }
}


//DFS(BST) O(V) O(H)
class Solution98_2 {
    public boolean isValidBST(TreeNode root) {
        long minVal = Long.MIN_VALUE; // 用long避免極值問題, 否則 left=Integer.MIN_VALUE會比較不出來
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= minVal) { // BST嚴格比較, 等於代表不符合
                return false;
            }
            minVal = root.val;
            root = root.right;
        }
        return true;
    }
}