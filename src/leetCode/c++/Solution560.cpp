//O(n) O(n)
class Solution560 {
    public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> cnt = {{0, 1}};//<累計總和, 該總和出現的次數>
        int res = 0, sum = 0;
        for(int num : nums){
            sum += num;
            res += cnt[sum - k];//累計sum若去掉(sum-k)即為k
            ++cnt[sum];
        }
        return res;
    }
};