package leetCode.java;

import java.util.*;

//DFS O(V) O(H)
class Solution437 {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> sumCnt = new HashMap<>();
        sumCnt.put(0L, 1);//記得放入初始位置
        return dfs(0, sumCnt, root, targetSum);
    }

    public int dfs(long sum, Map<Long, Integer> sumCnt, TreeNode root, int target){
        if(root == null) return 0;

        int res = 0;
        sum += root.val;
        if(sumCnt.containsKey(sum - target))
            res += sumCnt.get(sum - target);

        sumCnt.put(sum, sumCnt.getOrDefault(sum, 0) + 1);
        res += dfs(sum, sumCnt, root.left, target);
        res += dfs(sum, sumCnt, root.right, target);
        sumCnt.put(sum, sumCnt.get(sum) - 1);

        return res;
    }
}