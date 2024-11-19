//O(k) O(1), k = ops.size()
class Solution598 {
public:
    int maxCount(int m, int n, vector<vector<int>>& ops) {
        for (vector<int> op : ops) { // 此題只需要最大數字的數量, 不用差分算法
            m = min(m, op[0]);
            n = min(n, op[1]);
        }
        return m * n;
    }
};