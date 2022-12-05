//Greedy O(n) O(n)
class Solution128 {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> memo(nums.begin(), nums.end());
        int res;
        for(int num : nums){
            if(memo.count(num - 1) == 0){
                int cnt = 0;
                while(memo.count(num + cnt)) cnt++;
                res = max(res, cnt);
            }
        }
        return res;
    }
};