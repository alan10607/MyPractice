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
        /* nums = [1,2,3]
                            []
                    1                []
               12       1       2        []
            123  12   13  1   23  2     3  []
        */
    }

    //Time Complexity: O((2^n)n), Space Complexity: O(n), n = nums.length, 此時間大於排序所需
    //DFS
    class Solution39 {
        public List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            //用排序
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<Integer>());
            return res;
        }

        public void dfs(int[] candidates, int target, int index, List<Integer> nums) {
            if(target == 0){//先判斷成功
                res.add(new ArrayList<Integer>(nums));//要nums一個新的, 非nums本身
                return;
            }
            if(target < 0 || index == candidates.length) return;//超過大小, 或查完index

            //跳過
            dfs(candidates, target, index + 1, nums);

            //繼續使用
            nums.add(candidates[index]);
            dfs(candidates, target - candidates[index], index, nums);
            nums.remove(nums.size() - 1);//記得要移除
        }
        /* 分成繼續使用這個數或跳過這個數
        candidates = [2,3,6,7], target = 7
                                        []
                        2                             []
                22              2               3           []
            222     22      23      2       33      3    6      []
                 223  22      23                              7    []
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
        /* nums = [1,2,3]
                1            2            3
            12     13    21     23    31     32
            123    132   213    231   312    321
        */
    }

    //Time Complexity: O((2^n)n), Space Complexity: O(n), n = nums.length, 此時間大於排序所需
    //DFS
    class Solution90 {
        public List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);//O(nlogn)
            dfs(nums, 0, new ArrayList<Integer>());
            return res;
        }

        public void dfs(int[] nums, int index, List<Integer> numsList){
            if(index == nums.length){
                res.add(new ArrayList<Integer>(numsList));
                return;
            }

            //加上這個
            numsList.add(nums[index]);
            dfs(nums, index + 1, numsList);
            numsList.remove(numsList.size() - 1);

            //跳過
            while(index + 1 < nums.length && nums[index] == nums[index + 1])
                index++;//跳過的狀態下, 若選擇的是上一層沒跳過的, 則都跳過

            dfs(nums, index + 1, numsList);
        }
        /* nums = [1,2,2,3], 跳過的狀態下, 若選擇的是上一層沒跳過的, 則都跳過
                            []
                        1                       []
                12              1        2              []
            122    12         13  1   22    2          3  []
        1223 122  123 12            223 22 23 2
        */
    }

    //Time Complexity: O((2^n)n), Space Complexity: O(n), n = nums.length, 此時間大於排序所需
    //DFS
    class Solution40 {
        public List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            //用排序
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<Integer>());
            return res;
        }

        public void dfs(int[] candidates, int target, int index, List<Integer> nums) {
            if(target == 0){//先判斷成功
                res.add(new ArrayList<Integer>(nums));//要nums一個新的, 非nums本身
                return;
            }
            if(target < 0 || index == candidates.length) return;//超過大小, 或查完index

            //繼續使用
            nums.add(candidates[index]);
            dfs(candidates, target - candidates[index], index + 1, nums);
            nums.remove(nums.size() - 1);//記得要移除

            //跳過
            while(index + 1 < candidates.length && candidates[index] == candidates[index + 1])
                index++;//避免重複要跳過: 跳過的狀態下, 若選擇的是上一層沒跳過的, 則都跳過

            dfs(candidates, target, index + 1, nums);
        }
        /* 分成繼續使用這個數或跳過這個數
        candidates = [1,2,2,2,5], target = 5
                                        []
                        1                               []
                12              1               2               []
            122    12                       22      2       5       []
        */
    }

    //Time Complexity: O((n!)n), Space Complexity: O(n), n = s.length()
    //DFS
    class Solution131 {
        public List<List<String>> res = new ArrayList<>();

        public List<List<String>> partition(String s) {
            dfs(s, new ArrayList<String>());
            return res;
        }

        public void dfs(String s, List<String> strList){
            if(s.length() == 0){
                res.add(new ArrayList<String>(strList));
                return;
            }

            for(int i=1; i<=s.length(); i++){//從1開始, 要有等於
                if(isPali(s.substring(0, i))){
                    strList.add(s.substring(0, i));
                    dfs(s.substring(i), strList);
                    strList.remove(strList.size() - 1);
                }
            }
        }

        public boolean isPali(String s){//O(n)
            int l = 0;
            int r = s.length() - 1;

            while(l < r){
                if(s.charAt(l) != s.charAt(r))
                    return false;

                l++;
                r--;
            }
            return true;
        }
        /* s = "aab"
                    []
            a       aa      aab
          a   ab    b        x
          b   x
        */
    }

    //Time Complexity: O(3^m 4^n), Space Complexity: O(m + n), m為3個英文的數字(2, 3, 4, 5, 6, 8), n為4個英文的數字(7, 9)
    //DFS
    class Solution17 {
        public List<String> res = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if(digits.length() == 0) return res;

            HashMap<Character, String> phone = new HashMap<>();
            phone.put('2', "abc");
            phone.put('3', "def");
            phone.put('4', "ghi");
            phone.put('5', "jkl");
            phone.put('6', "mno");
            phone.put('7', "pqrs");
            phone.put('8', "tuv");
            phone.put('9', "wxyz");

            dfs(digits, 0, phone, "");
            return res;
        }


        public void dfs(String digits, int index, HashMap<Character, String> phone, String str){
            if(index == digits.length()){
                res.add(str);
                return;
            }

            if(digits.charAt(index) == '0' || digits.charAt(index) == '1'){
                dfs(digits, index + 1, phone, str);
            }else{
                String eng = phone.get(digits.charAt(index));
                for(int i=0; i<eng.length(); i++)
                    dfs(digits, index + 1, phone, str + eng.substring(i, i + 1));
            }
        }
    }

}