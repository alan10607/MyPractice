//Heap O(nlogk) O(n), n = nums.size()
class Solution347 {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for(int num : nums)
            ++cnt[num];

        //小排到大
        auto comp = [](pair<int, int>& a, pair<int, int>& b){
                return a.second > b.second;
            };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(comp)> pq(comp);
        for(auto it : cnt){
            pq.push({it.first, it.second});
            if(pq.size() > k)
                pq.pop();
        }

        vector<int> res;
        while(!pq.empty()){
            res.push_back(pq.top().first);
            pq.pop();
        }

        return res;
    }
};