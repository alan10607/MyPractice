//Greedy Kadane's Algorithm O(n) O(1)
class Solution53 {
public:
    int maxSubArray(vector<int>& nums) {
        int res = INT_MIN;
        int sum = 0;
        for(int num : nums){
            sum = max(sum + num, num);//若num較大, 則捨去之前
            res = max(res, sum);
        }
        return res;
    }
};