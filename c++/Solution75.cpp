//LR Pointer O(n) O(1)
class Solution75 {
public:
    void sortColors(vector<int>& nums) {
        //one-pass algorithm using only constant extra space
        int l = 0, r = nums.size() - 1, cur = 0;
        while(cur <= r){//r代表還未宦換位的2, 所以可以包涵
            if(nums[cur] == 0){
                swap(nums[l++], nums[cur++]);
            }else if(nums[cur] == 2){
                swap(nums[cur], nums[r--]);
            }else{//nums[cur] == 1
                cur++;
            }
        }
    }
};
/*
遇到0: 與l交換, 並移動l與cur
遇到1: 移動cur
遇到2: 與r交換, 並移動r

2   0   2   1   1   0
l/c                 r

0   0   2   1   1   2
l/c             r

0   0   2   1   1   2
    l/c         r

0   0   2   1   1   2
        l/c     r

0   0   1   1   2   2
        l/c r

0   0   1   1   2   2
        l   r/c
*/