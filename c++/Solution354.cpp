//DP Binary Search O(nlogn) O(n), n = envelopes.size()
class Solution354 { //solution300 LIS的二維應用
public:
    int maxEnvelopes(vector<vector<int>>& envelopes) {
        auto comp = [](vector<int>& a, vector<int>& b) {
            return (a[0] == b[0]) ? (a[1] > b[1]) : (a[0] < b[0]); // w相同時, h大排到小, 否則w小排到大
        };
        sort(envelopes.begin(), envelopes.end(), comp);

        vector<int> piles;
        for (vector<int> envelope : envelopes) {
            int height = envelope[1];
            auto it = lower_bound(piles.begin(), piles.end(), height);
            if (it == piles.end()) {
                piles.push_back(height);
            } else {
                *it = height;
            }
        }
        return piles.size();
    }
};
/*
w升序+h降序, 這樣保證後面用找h的LIS時不會出現w相同的情況
ex: envelopes=[[2,4],[1,2],[1,3]]

排序後envelopes=[[1,2],[1,3],[2,4]] -> h的LIS是[2,3,4], 但[1,2]不能套入[1,3], 不符合題意

如果w升序 + h降序:
排序後envelopes=[[1,3],[1,2],[2,4]] -> h的LIS是[3,4]or[2,4], 符合題意

*/


//DP O(n^2) O(n), n = envelopes.size()
class Solution354_2 { // 用DP O(n^2)的方法會TLE
public:
    int maxEnvelopes(vector<vector<int>>& envelopes) {
        sort(envelopes.begin(), envelopes.end()); // 先確保w是小到大, 再來看h, 就是找h的LIS

        int res = 1;
        vector<int> dp(envelopes.size(), 1);
        for (int i = 0; i < envelopes.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) { // w與h都比上一個大
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
            res = max(res, dp[i]);
        }
        return res;
    }
};