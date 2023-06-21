package leetCode.java;

import java.util.*;

//DFS(BST) O(H + k) O(H), H為高度, 時間複雜度供需要先下H, 再上k
class Solution230 {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> deque = new LinkedList<>();

        //root=right=null時若deque還有, 則會跳過內部while
        while(root != null || !deque.isEmpty()){
            while(root != null){
                deque.push(root);//從頭加入
                root = root.left;
            }

            root = deque.poll();
            if(--k == 0) return root.val;

            root = root.right;
        }
        return -1;
    }
}