package leetCode.java;

import java.util.*;

//Backtracking O(V) O(V)
class Solution105 {
    public int preIndex = 0;//dfs剛好是pre-order順序

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();//紀錄inorder的值與位置, <數字, 位置>
        for(int i=0; i<inorder.length; i++)
            inMap.put(inorder[i], i);

        return build(0, preorder.length - 1, preorder, inMap);
    }

    public TreeNode build(int start, int end, int[] preorder, Map<Integer, Integer> inMap){
        if(start > end) return null;

        int num = preorder[preIndex++];
        int inIndex = inMap.get(num);
        TreeNode root = new TreeNode(num);
        root.left = build(start, inIndex - 1, preorder, inMap);
        root.right = build(inIndex + 1, end, preorder, inMap);
        return root;
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