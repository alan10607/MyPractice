package leetCode.java;

import java.util.*;

//Backtracking O(n * n^2) O(target), n = candidates.length
class Solution39 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(0, new ArrayList<Integer>(), candidates, target);
        return res;
    }

    public void backtracking(int i, List<Integer> list, int[] candidates, int target){
        if(target == 0){//先判斷成功避免i==candidates.length()時return
            res.add(new ArrayList<Integer>(list));//O(n)
            return;
        }
        if(i == candidates.length || target < 0) return;

        backtracking(i + 1, list, candidates, target);

        list.add(candidates[i]);
        backtracking(i, list, candidates, target - candidates[i]);
        list.remove(list.size() - 1);
    }
}
/* 背包問題, 可以重複 candidates = [2,3,6,7], target = 7
				[]
		2				[]
	22		2		3		[]
  222  22		  33  3    6  []
	...
*/