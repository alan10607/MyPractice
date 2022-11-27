//DP NP-Complete O(n^2) O(n), O(n^2) = O(n * (target * 2)), 其實target就是n的一半
class Solution416 {
public:
    bool canPartition(vector<int>& nums) {
        int sum = 0;
        for(int num : nums) sum += num;

        if(sum % 2 != 0) return false;
        
        int target = sum / 2;
        vector<bool> dp(target + 1);
        dp[0] = true;
        for(int num : nums){
            for(int i = target; i - num >= 0; --i){
                //vector<bool>沒有重載|=的寫法, 它的reference實際上是特殊的class
                dp[i] = dp[i] | dp[i - num];
            }
        }
        return dp[target];
    }
};
/* nums = [1,5,11,5]
    0   1   2   3   4   5   6   7   8   9   10  11
1   T   T
5   T   T               T   T
11  T   T               T   T                   T
5   T   T               T   T               T   T
*/