//Greedy O(n) O(1)
class Solution55 {
public:
    bool canJump(vector<int>& nums) {
        int rightMost = 0;
        for(int i=0; i<nums.size(); ++i){
            if(i > rightMost) return false;
            rightMost = max(rightMost, i + nums[i]);
        }
        return true;
    }
};