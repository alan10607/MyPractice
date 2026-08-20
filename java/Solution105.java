package leetCode.java;

import java.util.*;

//Backtracking O(V) O(V)
class Solution105 {
    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderPos = new HashMap<>(); // <inorder val, index>
        for (int i = 0; i < inorder.length; ++i) {
            inorderPos.put(inorder[i], i);
        }
        return dfs(preorder, inorderPos, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] preorder, Map<Integer, Integer> inorderPos, int inStart, int inEnd) {
        if (inStart > inEnd) { // inStart == inEnd 代表還有一個node, 不能return
            return null;
        }
        int val = preorder[preIndex++];
        TreeNode node = new TreeNode(val);
        int mid = inorderPos.get(val);
        node.left = dfs(preorder, inorderPos, inStart, mid - 1);
        node.right = dfs(preorder, inorderPos, mid + 1, inEnd);
        return node;
    }
}
/*
preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
9 3 15,20,7
  ^
9 3 15,20,7
^ ^
9 3 15 20 7
^ ^    ^
9 3 15 20 7
^ ^ ^  ^
9 3 15 20 7
^ ^ ^  ^  ^
*/