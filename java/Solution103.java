package leetCode.java;

import java.util.*;

//BFS O(V) O(V)
class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        boolean goRight = true;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>(size);
            for(int i=0; i<size; i++)
                level.add(0);

            for(int i=0; i<size; i++) {
                TreeNode node = q.poll();
                int index = goRight ? i : (size - 1 - i);
                level.set(index, node.val);
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            res.add(level);
            goRight = !goRight;
        }
        return res;
    }
}