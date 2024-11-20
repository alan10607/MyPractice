//O(n) O(n)
class Solution560 {
public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> cnt = {{0, 1}}; //<累計總和, 該總和出現的次數>
        int res = 0, sum = 0;
        for (int num : nums) {
            sum += num;
            res += cnt[sum - k]; // 累計sum若去掉(sum-k)即為k
            ++cnt[sum]; // 先計算res, 再增加cnt, 避免k=0的時候直接計算到
        }
        return res;
    }
};
/*
num     1   2   1   2   1
sum     0   1   3   4   6   7
        --------- res+=cnt[0]
            --------- res+=cnt[1]
                --------- res+=cnt[3]
                    --------- res+=cnt[4]

題目問k=3, 有幾個subarray總和為3
需要查看所有 sum-k 有幾段成立
查得res=4


*/