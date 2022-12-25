//Intervals O(nlogn) O(n)
class Solution435 {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        //這題排序若無取址&的話會TLE
        sort(intervals.begin(), intervals.end(), [](vector<int>& a, vector<int>& b){
                return a[1] < b[1];//依右側小排到大
            });
        
        int res = 0, right = INT_MIN;
        for(vector<int> interval : intervals){
            if(interval[0] < right){
                ++res;//跳過這個
            }else{
                right = interval[1];
            }
        }
        return res;
    }
};
/* 優先去除end較大的那個
------
       -------
-----------------

*/