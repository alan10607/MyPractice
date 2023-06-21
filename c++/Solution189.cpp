//O(n) O(1)
class Solution189 {
public:
    void rotate(vector<int>& nums, int k){
        //with O(1) extra space
        if(k % nums.size() == 0) return;//回到原點

        int start = 0, num = nums[0], i = 0;
        for(int j=0; j<nums.size(); ++j){
            i = (i + k) % nums.size();
            int temp = nums[i];
            nums[i] = num;
            num = temp;
            if(i == start){//防止nums.size()為k倍數時回到自己
                i = ++start;
                num = nums[i];
            }
        }
    }
};
/* nums = [1,2,3,4,5,6,7], k = 3
1   2   3   4   5   6   7
            1           4
        7           3
    6           2
5

*/