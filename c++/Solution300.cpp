//DP O(n^2) O(n), n = nums.size()
class Solution300 {
public:
    int lengthOfLIS(vector<int>& nums) {
        vector<int> dp(nums.size(), 1);

        int res = 1;
        for (int i = 1; i < nums.size(); ++i) {
            for (int j = 0; j < i; ++j) { // 尋找前一個更小的數
                if (nums[i] > nums[j]) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
            res = max(res, dp[i]);
        }
        return res;
    }
};
/* nums = [0,1,0,3,2,3]
nums    0   1   0   3   2   3
dp      1   2   1   3   3   4

*/


//DP Binary Search O(nlogn) O(n), n = nums.size()
class Solution300_2 {
public:
    int lengthOfLIS(vector<int>& nums) {
        vector<int> piles; // 每堆最上面的牌
        for (int num : nums) {
            auto it = lower_bound(piles.begin(), piles.end(), num); // 尋找piles中的左側邊界(第一個比num大的)
            if (it == piles.end()) {
                piles.push_back(num); // 如果都比這些堆最上面的牌大, 則再建立一堆
            } else {
                *it = num; // 蓋牌到該堆最上面
            }
        }
        return piles.size();
    }
};
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

此時堆的數量就是LIS, 此例LIS=5
*/