package leetCode.java;

import java.util.*;

//Backtracking O(n * n!) O(n), 時間複雜度要進行(n!)次backtracking, 每次要跑一次for(n)
class Solution46 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        dfs(new ArrayList<Integer>(), nums, visited);
        return res;
    }

    public void dfs(List<Integer> list, int[] nums, Set<Integer> visited){
        if(list.size() == nums.length){
            res.add(new ArrayList<Integer>(list));//記得new一個新的
            return;
        }

        for(int num : nums){
            if(!visited.contains(num)){
                visited.add(num);
                list.add(num);
                dfs(list, nums, visited);
                list.remove(list.size() - 1);
                visited.remove(num);
            }
        }
    }
}
/* 不可重複 nums = [1,2,3]
						[]
		1				2				3
	12		13		21		23		31		32
	123		132		213		231		312		321
*/