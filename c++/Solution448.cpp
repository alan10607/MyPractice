//O(n) O(1)
class Solution448 {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        for(int i=0; i<nums.size(); ++i){
            while(nums[i] != nums[nums[i] - 1]){
                swap(nums[i], nums[nums[i] - 1]);
            }
        }

        vector<int> res;
        for(int i=0; i<nums.size(); ++i)
            if(i != nums[i] - 1) res.push_back(i + 1);

        return res;
    }
};