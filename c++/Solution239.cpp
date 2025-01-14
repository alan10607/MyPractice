class Solution239 {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> res;
        deque<int> q; // 保持大到小, 遞減數列

        for (int i = 0; i < nums.size(); ++i) {
            if (i - k == q.front()) {
                q.pop_front();
            }

            while (!q.empty() && nums[q.back()] < nums[i]) {
                q.pop_back();
            }
            q.push_back(i);

            if (i - k + 1 >= 0) {
                res.push_back(nums[q.front()]);
            }
        }
        return res;
    }
};
/*
deque:

前              後
    |
    |       |
    |   |   |
    |   |   |   |
    ^   ^從back移除太小的, 使deque保持單調遞減        
    |   
    從front移除太舊的

*/
