//2D-DP O(n^2) O(n)
class Solution673 {
public:
    int findNumberOfLIS(vector<int>& nums) {
        int n = nums.size();
        vector<int> len(n, 1);//nums[i]為終點的最長長度
        vector<int> cnt(n, 1);//nums[i]為終點的最長長度的個數
        int maxLen = 1, res = 0;

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] >= nums[i]) continue;//非遞增

                if (len[j] + 1 > len[i]) {
                    len[i] = len[j] + 1;//更新最長長度
                    cnt[i] = cnt[j];
                } else if (len[j] + 1 == len[i]) {
                    cnt[i] += cnt[j];//同為最長長度, 個數+1
                }
            }
            maxLen = max(maxLen, len[i]);
        }

        for (int i = 0; i < n; ++i) {//加總所有最大個數, 有重複相同的最大值時會在這裡加總
            if (len[i] == maxLen) res += cnt[i];
        }
        return res;
    }
};