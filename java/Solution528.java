package leetCode.java;

import java.util.*;

//Binary Search Solution(): O(n) O(n), pickIndex(): O(logn) O(n)
class Solution {//Solution528
    List<Integer> nums = new ArrayList<>();

    public Solution(int[] w) {
        for(int num : w)//累加
            nums.add(nums.isEmpty() ? num : nums.get(nums.size() - 1) + num);
    }

    public int pickIndex() {
        int rand = (int) (Math.random() * nums.get(nums.size() - 1));//0<=rand<最大
        return binarySearch(nums, rand);
    }

    public int binarySearch(List<Integer> nums, int target){
        int l = 0, r = nums.size() - 1;
        while(l < r){
            int mid = (l + r) / 2;
            if(nums.get(mid) <= target){//取得比target大的最小數
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return r;
    }
}
/*
因為rand不包含最大數, 因此二分法取upper_bound

nums = 1,2,3  sum = 1,3,6
0 <= rand < 6
0 <= rand < 1 , 0     => 1
1 <= rand < 3 , 1,2   => 2
3 <= rand < 6 , 3,4,5 => 3


0   1   2   3   4   5   6
|---
1   |-------
    2       |------------
            3
*/