package leetCode.java;

import java.util.*;

//DFS(BST) O(V) O(H)
class Solution98 {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        long num = Long.MIN_VALUE;

        //in-order
        while(!deque.isEmpty() || root != null){
            while (root != null) {
                deque.push(root);//進while再push
                root = root.left;
            }

            root = deque.poll();
            if (num >= root.val) return false;//包含等於
            num = root.val;//更新目前最小數字

            root = root.right;
        }
        return true;
    }
}