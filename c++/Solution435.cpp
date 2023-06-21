//Intervals O(nlogn) O(n)
class Solution435 {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        int right = INT_MIN, res = 0;
        for(auto interval : intervals){
            if(interval[0] < right){//前一個的右側大於這左側
                res++;
                right = min(right, interval[1]);//取較短的, 即捨棄長的
            }else{
                right = interval[1];
            }
        }
        return res;
    }
};