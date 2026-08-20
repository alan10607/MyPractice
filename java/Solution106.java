package leetCode.java;

import java.util.*;

//Backtracking O(V) O(V)
class Solution106 {
    int postIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        Map<Integer, Integer> inorderPos = new HashMap<>(); // <inorder val, index>
        for (int i = 0; i < inorder.length; ++i) {
            inorderPos.put(inorder[i], i);
        }
        return dfs(postorder, inorderPos, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] postorder, Map<Integer, Integer> inorderPos, int inStart, int inEnd) {
        if (inStart > inEnd) { // inStart == inEnd 代表還有一個node, 不能return
            return null;
        }
        int val = postorder[postIndex--];
        TreeNode node = new TreeNode(val);
        int mid = inorderPos.get(val);
        node.right = dfs(postorder, inorderPos, mid + 1, inEnd); // postorder要先從右邊建立, 因為postorder: L -> R -> root
        node.left = dfs(postorder, inorderPos, inStart, mid - 1);
        return node;
    }
}