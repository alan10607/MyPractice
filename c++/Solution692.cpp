//Heap O(nlogk) O(n), n = words.size()
class Solution692 {
public:
    vector<string> topKFrequent(vector<string>& words, int k) {
        unordered_map<string, int> cnt; // <word, 出現次數>
        for (string word : words) {
            ++cnt[word];
        }

        // If same frequency, sort by lexicographical order
        // 出現次數小排到大(a > b, min heap), 子母z排到a=大排到小(a < b, max heap)
        auto comp = [](pair<string, int> a, pair<string, int> b) {
            return a.second == b.second ? (a.first < b.first) : (a.second > b.second);
        };
        priority_queue<pair<string, int>, vector<pair<string, int>>, decltype(comp)> pq(comp); // <<word, 次數>, ...>

        for (auto it : cnt) {
            pq.push(it);
            if (pq.size() > k) {
                pq.pop(); // 跳掉多餘的
            }
        }

        vector<string> res(k);
        for (int i = res.size() - 1; i >= 0; --i) {
            res[i] = pq.top().first;
            pq.pop();
        }
        return res;
    }
};