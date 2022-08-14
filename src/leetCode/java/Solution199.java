package leetCode.java;

import java.util.*;

//BFS O(V) O(V)
class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        //不只有最右側的分支
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int rightNum = -1;
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                rightNum = node.val;

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(rightNum);
        }
        return res;
    }
}