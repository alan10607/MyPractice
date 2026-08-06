package leetCode.java;

import java.util.*;

//Backtracking Knapsack O(n * 2^n) O(target), n = candidates.size(), DFS每次new ArrayList需要O(n), 共O(n * 2^n), 此時間複雜度大於排序所需
class Solution40 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 排序後相同元素會相鄰，方便跳過重複組合
        backtracking(0, new ArrayList<>(), candidates, target);
        return res;
    }

    public void backtracking(int start, List<Integer> selected, int[] nums, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(selected));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < nums.length; ++i) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue; // 一樣的數字的話要剪掉, 否則backtracking會再處理一次
            }
            selected.add(nums[i]);
            backtracking(i + 1, selected, nums, target - nums[i]);
            selected.remove(selected.size() - 1);
        }
    }
}