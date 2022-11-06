//Heap O(nlogk) O(k)
class Solution973 {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        //{距離, points index}, 大排到小, pair會先比較first再second
        priority_queue<pair<int,int>, vector<pair<int,int>>, less<pair<int,int>>> pq;
        for(int i=0; i<points.size(); i++){
            int len = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            pq.push({len, i});

            if(pq.size() > k)
                pq.pop();
        }

        vector<vector<int>> res;
        while(!pq.empty()){
            auto data = pq.top(); pq.pop();
            res.push_back(points[data.second]);
        }
        return res;
    }
};