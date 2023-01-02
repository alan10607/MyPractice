//Intervals Heap O(nlogn + qlogq) O(n + q), n = intervals.size(), q = queries.size()
class Solution1851 {
public:
    vector<int> minInterval(vector<vector<int>>& intervals, vector<int>& queries) {
        vector<pair<int, int>> ques;//<<數字, 原數組的位置>, ...>
        for(int i=0; i<queries.size(); ++i)
            ques.push_back({queries[i], i});

        sort(intervals.begin(), intervals.end());//依照start小到大
        sort(ques.begin(), ques.end());//依照數字小到大
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;//<長度, end>, 依長度小到大
        int i = 0;
        vector<int> res(queries.size());
        for(auto que : ques){
            while(i < intervals.size() && intervals[i][0] <= que.first){//加入範圍內
                pq.push({intervals[i][1] - intervals[i][0] + 1, intervals[i][1]});
                ++i;
            }

            while(!pq.empty() && pq.top().second < que.first){
                pq.pop();//移除範圍外
            }

            res[que.second] = pq.empty() ? -1 : pq.top().first;
        }
        return res;
    }
};
/* intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]

            s   e   len
q=2 heap=   2   3   2
            2   5   4
            1   8   8
q=5 heap=   2   5   4
            1   8   8
q=19heap=
q=22heap=   20  25  5
*/