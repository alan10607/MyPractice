//Bit O(n) O(1)
class Solution136 {
public:
    int singleNumber(vector<int>& nums) {
        int res = 0;
        for(int num : nums)
            res ^= num;

        return res;
    }
};