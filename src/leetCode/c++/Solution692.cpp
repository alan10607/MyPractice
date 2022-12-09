//Heap O(nlogk) O(n), n = words.size()
class Solution692 {
public:
    vector<string> topKFrequent(vector<string>& words, int k) {
        //if same frequency, sort by lexicographical order
        unordered_map<string, int> cnt;
        for(string word : words)
            ++cnt[word];

        //逆序入列: cnt小排到大, 字母序大排到小
        auto comp = [](pair<string, int>& a, pair<string, int>& b){
                return a.second == b.second ? a.first < b.first : a.second > b.second;
            };
        priority_queue<pair<string, int>, vector<pair<string, int>>, decltype(comp)> pq(comp);//<<word, 次數>, ...>
        for(auto it : cnt){
            pq.push(it);
            if(pq.size() > k)
                pq.pop();//跳掉多餘的
        }

        vector<string> res(pq.size());
        for(int i = res.size() - 1; i>=0; --i){
            res[i] = pq.top().first;
            pq.pop();
        }
        return res;
    }
};