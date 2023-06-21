//Intervals O(nlog) O(n), sort需要O(nlogn) O(n)
class Solution253 {//lintcode919
public:
    int minMeetingRooms(vector<Interval> &intervals) {
        vector<int> start;
        vector<int> end;
        for(Interval interval : intervals){
            start.push_back(interval.start);
            end.push_back(interval.end);
        }

        sort(start.begin(), start.end());
        sort(end.begin(), end.end());

        int s = 0, e = 0, cnt = 0, res = 0;
        while(s < start.size()){
            if(start[s] < end[e]){
                ++cnt;
                ++s;
                res = max(res, cnt);
            }else{
                --cnt;
                ++e;
            }
        }
        return res;
    }
};
/* intervals = [(0,30),(5,10),(15,20)]
0   5   10  15  20      30
--------------------------
    ------  ------

*/