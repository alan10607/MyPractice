//Moore Voting Algorithm O(n) O(1)
class Solution169 {
public:
    int majorityElement(vector<int>& nums) {
        //solve the problem in linear time and in O(1) space
        //只有超過半數才能用莫耳演算法
        int res = -1, cnt = 0;
        for(int num : nums){
            if(cnt == 0){
                res = num;
                ++cnt;
            }else{
                res == num ? ++cnt : --cnt;
            }
        }
        return res;
    }
};