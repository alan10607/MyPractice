package leetCode.java;

import java.util.*;

//Backtracking O(n * 2^n) O(n)
class Solution78 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(0, new ArrayList<>(), nums);
        return res;    
    }

    public void backtracking(int start, List<Integer> selected, int[] nums) {
        res.add(new ArrayList<>(selected)); // O(n), 記得建立新物件

        for (int i = start; i < nums.length; ++i) {
            selected.add(nums[i]);
            backtracking(i + 1, selected, nums);
            selected.remove(selected.size() - 1);
        }
    }
}
/*
nums = [1,2,3]
                        []
        1               2               3
    12     13           23  
    123  
*/