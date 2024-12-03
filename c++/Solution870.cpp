//Two Pointer O(nlogn) O(n)
class Solution870 {
public:
    vector<int> advantageCount(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end()); // 小到大
        auto comp = [](pair<int, int> a, pair<int, int> b) {
            return a.second < b.second; // 大到小, max heap
        };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(comp)> pq(comp); // <位置, 數字>
        for (int i = 0; i < nums2.size(); ++i) {
            pq.push({i, nums2[i]});
        }

        int l = 0, r = nums1.size() - 1; // l=nums1的最小值, r=nums1的最大值
        vector<int> res(nums1.size());
        while (!pq.empty()) {
            auto pair = pq.top(); pq.pop();
            int i = pair.first;
            int num = pair.second;
            if (nums1[r] > num) { // 比得過, 就上
                res[i] = nums1[r];
                --r;
            } else { // 比不上, 就拿最小的送分
                res[i] = nums1[l];
                ++l;
            }
        }

        return res;
    }
};