package leetCode.java;

import java.util.*;

//BFS O(V) O(V)
class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int rightVal = -1;
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.poll();
                rightVal = node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(rightVal);
        }
        return res;
    }
}