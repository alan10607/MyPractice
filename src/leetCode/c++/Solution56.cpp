//Intervals O(nlogn) O(n), sort需要O(nlogn) O(n)
class Solution56 {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        //依照左界小排到大
        if (intervals.empty()) return {};
        sort(intervals.begin(), intervals.end(), [](vector<int>& a, vector<int>& b){
                return a[0] < b[0];
            });
        vector<vector<int>> res{intervals[0]};
        for (int i = 1; i < intervals.size(); ++i) {
            if (res.back()[1] < intervals[i][0]) {
                res.push_back(intervals[i]);
            } else {
                res.back()[1] = max(res.back()[1], intervals[i][1]);
            }
        }
        return res;
    }
};