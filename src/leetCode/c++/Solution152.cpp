//Greedy Kadane's Algorithm O(n) O(1)
class Solution152 {
public:
    int maxProduct(vector<int>& nums) {
        int maxPro = 1, minPro = 1, res = INT_MIN;
        for(int num : nums){
            int temp = maxPro;
            maxPro = max(num, max(maxPro * num, minPro * num));
            minPro = min(num, min(temp * num, minPro * num));
            res = max(res, maxPro);
        }
        return res;
    }
};