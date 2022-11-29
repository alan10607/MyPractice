//Intervals O(nlogn) O(n), sort需要O(nlogn) O(n)
class Solution252 {//lintcode920
public:
    bool canAttendMeetings(vector<Interval> &intervals) {
        sort(intervals.begin(), intervals.end(), [](Interval& a, Interval& b){
                return a.start < b.start;//依照左界小排到大
            });

        for(int i=1; i<intervals.size(); ++i){
            if(intervals[i - 1].end > intervals[i].start)
                return false;
        }
        return true;
    }
};