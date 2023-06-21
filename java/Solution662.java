package leetCode.java;

import java.util.*;

//BFS O(V) O(V)
class Solution662 {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Object[]> q = new LinkedList<>();//<[node, 編號], ...>
        q.offer(new Object[]{root, 1});

        int res = 0;
        while(!q.isEmpty()){
            int l = (int) (q.peek()[1]), r = l;
            for(int i=q.size(); i>0; i--){
                Object[] data = q.poll();
                TreeNode node = (TreeNode) data[0];
                r = (int) data[1];
                if(node.left != null) q.offer(new Object[]{node.left, r * 2});
                if(node.right != null) q.offer(new Object[]{node.right, r * 2 + 1});
            }
            res = Math.max(res, (int) (r - l + 1));
        }
        return res;
    }
}