package leetCode.java;

import java.util.*;

//Backtracking O(n * n!) O(n)
class Solution77 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtracking(1, new ArrayList<>(), n, k);
        return res;
    }

    public void backtracking(int start, List<Integer> nums, int n, int k){
        if(nums.size() == k){
            res.add(new ArrayList(nums));
            return;
        }

        for(int i=start; i<=n; ++i){
            nums.add(i);
            backtracking(i + 1, nums, n, k);
            nums.remove(nums.size() - 1);
        }
    }
}