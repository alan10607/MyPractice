package leetCode.java;

import java.util.*;

//DFS(BST) O(H + k) O(H), H為高度, 時間複雜度供需要先下H, 再上k
class Solution230 {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }
}