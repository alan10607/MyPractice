package leetCode.java;

import java.util.*;

//DFS O(V^2) O(H)
class Solution113 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(new ArrayList<Integer>(), targetSum, root);
        return res;
    }

    public void dfs(List<Integer> nums, int target, TreeNode root){
        if(root == null) return;

        nums.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null)
            res.add(new ArrayList<>(nums));//記得new一個新的

        dfs(nums, target, root.left);
        dfs(nums, target, root.right);

        nums.remove(nums.size() - 1);
    }
}