package leetCode.java;

import java.util.*;

//Backtracking O(n * n!) O(n)
class Solution47 {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visited = new boolean[nums.length];//表示位置
        Arrays.sort(nums);//記得排序來判斷前一個是否相同
        backtracking(new ArrayList<>(), visited, nums);
        return res;
    }

    public void backtracking(List<Integer>list, boolean[] visited, int[] nums){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i=0; i<nums.length; i++){
            if(visited[i]) continue;
            if(i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            list.add(nums[i]);
            backtracking(list, visited, nums);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}