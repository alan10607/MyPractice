package leetCode.java;

import java.util.*;

//DFS O(n * 2^n) O(n), n = candidates.length, DFS每次new ArrayList需要O(n), 共O(n * 2^n), 此時間複雜度大於排序所需
class Solution40 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //The solution set must not contain duplicate combinations
        Arrays.sort(candidates);
        dfs(0, new ArrayList<Integer>(), candidates, target);
        return res;
    }

    public void dfs(int i, List<Integer> list, int[] candidates, int target){
        if(target == 0){//先判斷target, 避免i==candidates.length時就return
            res.add(new ArrayList<Integer>(list));//O(n)
            return;
        }
        if(target < 0 || i == candidates.length) return;//超過大小, 已經走完


        //選取
        list.add(candidates[i]);
        dfs(i + 1, list, candidates, target - candidates[i]);
        list.remove(list.size() - 1);

        //跳過
        while(i + 1 < candidates.length && candidates[i] == candidates[i + 1])
            i++;//繼續跳過

        dfs(i + 1, list, candidates, target);
    }
}
/* 有限制數量, 為背包問題不用DP
candidates = [1,1,1,2], target = 4
				[]
		1				[]
	2		1		1		[]
  3   2   2   1   2   1   1   []
 x 3 4 2 4 2 3 1 4 2 3 1 3 1 2 []

透過跳過已經跳過的數避免重複
				[]
		1				[]
	2		1		2		[]
  3   2   3   1
 x 3 4 2

res = 1,2,4
*/