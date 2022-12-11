package leetCode.java;

import java.util.*;

//O(n * logn * logZ) O(logn), n = nums.length, Z = num轉為字串的長度(最大32), 空間複雜度為排序所需
class Solution179 {
    public String largestNumber(int[] nums) {
        //Arrays.sort() lambda要放入包裝後的Integer或String, 先轉型
        String[] strs = new String[nums.length];
        for(int i=0; i<nums.length; i++)
            strs[i] = Integer.toString(nums[i]);

        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));//大排到小

        StringBuffer res = new StringBuffer();
        for(String str : strs)
            res.append(str);

        return res.charAt(0) == '0' ? "0" : res.toString();
    }
}