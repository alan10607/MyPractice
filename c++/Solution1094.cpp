//O(k + n) O(n), k = trips.size(), n = 站點數, 這裡直接用題目給的1001
class Solution1094 {
public:
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        vector<int> diff(1001, 0); // 題目給trips.length <= 1000
        for (vector<int> trip : trips) {
            int num = trip[0];
            int from = trip[1];
            int to = trip[2];
            diff[from] += num;
            diff[to] -= num; // to已經下車, 所以要增加的區間是[from, to), 跟別題有些是[l, r]不一樣
        }

        for (int i = 0; i < 1001; ++i) {
            if (i > 0) {
                diff[i] += diff[i - 1];
            }
            if (diff[i] > capacity) {
                return false;
            }
        }
        return true;
    }
};