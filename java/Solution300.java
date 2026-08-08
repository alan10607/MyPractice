package leetCode.java;

//DP O(n^2) O(n)
class Solution300 {
    public int lengthOfLIS(int[] nums) {
        // 基於Patience Sorting 耐心排序
        List<Integer> tops = new ArrayList<>(); // 每個牌堆最上面那張牌, 堆內保持遞減, tops保持遞增
        for (int num : nums) {
            int index = getLowerBound(tops, num);
            if (index == tops.size()) { // 表示num是目前cards中最大的
                tops.add(num); // 自己建立一堆
            } else {
                tops.set(index, num); // 更新這個牌堆的頂部, 保持縮小
            }
        }
        return tops.size();
    }

    public int getLowerBound(List<Integer> nums, int target) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
/**
基於Patience Sorting 耐心排序的解法, 這是一個用來排序撲克牌的方法:

只能將較小的牌放在該堆上, 如果當前牌比所有堆都大, 則創建一個新堆放入該牌
如果有多個堆可以放置當前牌, 則優先選擇最左邊的堆(lower_bound)
由左到右看每堆的top, 都會是小到大排序


ex:
num = 5,2,4,9,10,1,8,13,12,6,3,7,11

5   4   9   10  13
2   3   8   7   12
1       6       11

此時堆的數量就是LIS(1, 3, 6, 7, 11), 此例LIS=5
*/


class Solution300_2 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 至少每個dp都會有1
        int res = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) { // 計算區間[0,i)是否存在nums[j]<nums[i]
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
/*
ex: nums = [0,1,0,3,2,3]

i       0   1   2   3   4   5
nums    0   1   0   3   2   3
dp      1   2   1   3   3   4

dp[i] = 以 nums[i] 作為最後一個元素的 LIS 長度

如果 j < i 且 nums[j] < nums[i]
dp[i] = max(dp[i], dp[j] + 1)


ex2: nums = [1,2,4,3]
LIS[0] = 1, 至少都會有1即自己本身
LIS[1] = max(1, LIS[0]+1) = 2
LIS[2] = max(1, LIS[0]+1, LIS[1]+1) = 3
LIS[3] = max(1, LIS[0]+1, LIS[1]+1) = 3, 沒有LIS[2]因為nums[3] < nums[2]
...
LIS[n] = max(1, LIS[0]+1, ...)
*/