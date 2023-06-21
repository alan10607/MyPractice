//O(n) O(n)
class Solution1 {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> memo;
        for(int i=0; i<nums.size(); ++i){
            if(memo.count(target - nums[i])){
                return {memo[target - nums[i]], i};
            }else{
                memo[nums[i]] = i;
            }
        }
        return {};
    }
};