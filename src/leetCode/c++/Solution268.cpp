//Bit O(n) O(1)
class Solution268 {
public:
    int missingNumber(vector<int>& nums) {
        int res = 0;
        for(int i=0; i<=nums.size(); ++i)
            res ^= i;

        for(int num : nums)
            res ^= num;

        return res;
    }
};