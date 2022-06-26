package leetCode.javaCode;

import java.util.*;

/**
 *  Backtracking
 */
public class NeetCode150_Backtracking {

    //Time Complexity: O((2^n)n), Space Complexity: O(n), n = nums.length
    //DFS
    class Solution78 {
        public List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            dfs(nums, 0, new ArrayList<Integer>());
            return res;
        }

        public void dfs(int[] nums, int index, List<Integer> eles){
            if(index == nums.length){
                res.add(new ArrayList<Integer>(eles));//記得new一個新的, O(n)
                return;
            }

            //跳過這個
            dfs(nums, index + 1, eles);

            //加上這個
            eles.add(nums[index]);
            dfs(nums, index + 1, eles);
            eles.remove(eles.size() - 1);
        }
        /*
                            []
                    1                []
               12       1       2        []
            123  12   13  1   23  2     3  []
        */
    }

    //Time Complexity: O((n!)n), Space Complexity: O(n), n = nums.length
    //DFS
    class Solution46 {
        public List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            boolean[] visited = new boolean[nums.length];
            dfs(nums, visited, new ArrayList<Integer>());
            return res;
        }

        public void dfs(int[] nums, boolean[] visited, List<Integer> numList){
            if(numList.size() == nums.length){
                res.add(new ArrayList<Integer>(numList));//記得new一個新的, O(n)
                return;
            }

            for(int i=0; i<nums.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    numList.add(nums[i]);
                    dfs(nums, visited, numList);
                    numList.remove(numList.size() - 1);
                    visited[i] = false;
                }
            }
        }
        /*
                1            2            3
            12     13    21     23    31     32
            123    132   213    231   312    321
        */
    }


}