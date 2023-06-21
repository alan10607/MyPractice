package leetCode.java;

import java.util.*;

//Backtracking O(n * 2^n) O(n), 漸進意義上遞迴所需時間複雜度會大於排序所需
class Solution90 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);//記得背包問題要先排序
        backtracking(0, nums, new ArrayList<Integer>());
        return res;
    }

    public void backtracking(int i, int[] nums, List<Integer> list){
        if(i == nums.length){
            res.add(new ArrayList<Integer>(list));//O(n)
            return;
        }

        list.add(nums[i]);
        backtracking(i + 1, nums, list);
        list.remove(list.size() - 1);

        //The solution set must not contain duplicate subsets
        while(i + 1 < nums.length && nums[i] == nums[i + 1])
            i++;

        backtracking(i + 1, nums, list);
    }
}
/* 背包問題, 不可以重複 nums = [1,2,2]
				[]
		1				[]
	12		1		2		[]
  122 12  12  1   22  2   2   []
*/