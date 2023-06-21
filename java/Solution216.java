package leetCode.java;

import java.util.*;

//Backtracking O(k * (9! / (k! * (9 - k)!))) O(n), C9取k = 9! / (k! * (9 - k)!)
class Solution216 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(1, new ArrayList<>(), k, n);
        return res;
    }

    public void backtracking(int start, List<Integer> nums, int k, int n){
        if(nums.size() == k && n == 0){
            res.add(new ArrayList<>(nums));
            return;
        }
        if(nums.size() > k || n < 0) return;

        for(int i=start; i<=9; i++){
            nums.add(i);
            backtracking(i + 1, nums, k, n - i);
            nums.remove(nums.size() - 1);
        }
    }
}