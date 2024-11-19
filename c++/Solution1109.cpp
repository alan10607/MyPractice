//O(k + n) O(1), k = bookings.size(), 空間除了題目所需沒有其他故O(1)
class Solution1109 {
public:
    vector<int> corpFlightBookings(vector<vector<int>>& bookings, int n) {
        vector<int> res(n + 1, 0);
        for (vector<int> booking : bookings) { // 獲得diff
            int l = booking[0];
            int r = booking[1];
            int val = booking[2];
            res[l - 1] += val;
            res[r] -= val;
        }

        for (int i = 1; i < n; ++i) { // 加總diff
            res[i] += res[i - 1];
        }
        res.resize(n);
        return res;
    }
};