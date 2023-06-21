//Slide Window Intervals O(nm + k) O(nm), n = nums.size(), m = nums[0].size(), k為所有num(約為nm)用在slide window
class Solution632 {
public:
    vector<int> smallestRange(vector<vector<int>>& nums) {
        vector<pair<int, int>> all;//<<數字, 哪一組>, ...>
        for(int i=0; i<nums.size(); ++i){
            for(int num : nums[i]){
                all.push_back({num, i});
            }
        }
        sort(all.begin(), all.end());//數字小排到大

        vector<int> cnt(nums.size());//該組數字出現的次數
        vector<int> res;
        int check = 0, l = 0, r = 0, minLen = INT_MAX;
        while(r < all.size()){
            if(cnt[all[r].second]++ == 0)
                ++check;
            
            while(check == nums.size()){
                if(all[r].first - all[l].first < minLen){
                    minLen = all[r].first - all[l].first;
                    res = {all[l].first, all[r].first};
                }
                
                if(--cnt[all[l++].second] == 0)
                    --check;
            }
            
            ++r;
        }
        return res;
    }
};
/* nums = [[4,24],[0,9,20],[22,30]], 把問題轉為slide window

0   4   9   20  22  24  30
b   a   b   b   c   a   c
            ---------

*/