//Slide Window Stack O(n) O(k), n = nums.size()
class Solution239 {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        deque<int> q;//保持大到小, 遞減數列
        vector<int> res;
        for(int i=0; i<nums.size(); ++i){
            if(q.front() == i - k)
                q.pop_front();

            while(!q.empty() && nums[q.back()] < nums[i])
                q.pop_back();
            
            q.push_back(i);

            if(i >= k - 1)
                res.push_back(nums[q.front()]);
        }
        return res;
    }
};