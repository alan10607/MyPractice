//O(n) O(n)
class Solution525 {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int, int> memo = {{0, -1}};//<水平差, 位置>
        int res = 0, sum = 0;
        for(int i=0; i<nums.size(); ++i){
            sum += (nums[i] == 1 ? 1 : -1);
            if(memo.count(sum)){
                res = max(res, i - memo[sum]);
            }else{
                memo[sum] = i;//紀錄下第一個, 也就是最遠距離
            }
        }
        return res;
    }
};
/* 紀錄1個數-0個數的水平差, 然後在遍歷找尋同樣水平的最遠距離

nums        1   1   1   0   0   1   0   0
水平差   0   1   2   3   2   1   2   1   0

*/