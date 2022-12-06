//O(nlogn) O(n), n為schedule內所有時段長度
class Solution759 {//lintcode850
public:
    vector<Interval> employeeFreeTime(vector<vector<int>> &schedule) {
        vector<pair<int, int>> intervals;
        for(auto it : schedule){
            for(int i=0; i<it.size(); i+=2){
                intervals.push_back({it[i], it[i + 1]});
            }
        }

        sort(intervals.begin(), intervals.end(), less<pair<int, int>>());
        vector<Interval> res;
        int last = -1;
        for(auto interval : intervals){
            if(last != -1 && last < interval.first){
                res.push_back(Interval(last, interval.first));
            }
            last = max(last, interval.second);
        }
        return res;
    }
};