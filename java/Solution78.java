package leetCode.java;

import java.util.*;

//Backtracking O(n * 2^n) O(n)
class Solution78 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(0, new ArrayList<Integer>(), nums);
        return res;
    }

    public void backtracking(int i, List<Integer> list, int[] nums){
        if(i == nums.length){
            res.add(new ArrayList<Integer>(list));//O(n)
            return;
        }

        backtracking(i + 1, list, nums);

        list.add(nums[i]);
        backtracking(i + 1, list, nums);
        list.remove(list.size() - 1);
    }
}
/* 背包問題, 預設num不重複, nums = [1,2,3]
				[]
		1				[]
	12		1		2		[]
  123 12  13  1	  23  2   3   []
*/