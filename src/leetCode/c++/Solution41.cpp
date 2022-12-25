//O(n) O(1)
class Solution41 {
public:
    int firstMissingPositive(vector<int>& nums) {
        //must in O(n) time and uses constant extra space
        for(int i=0; i<nums.size(); ++i){
            while(nums[i] > 0 && nums[i] <= nums.size() && nums[i] != nums[nums[i] - 1]){
                swap(nums[i], nums[nums[i] - 1]);
            }
        }

        for(int i=0; i<nums.size(); ++i){
            if(nums[i] != i + 1) return i + 1;
        }

        return nums.size() + 1;
    }
};
/* 想辦法把num放到num-1的位子上

3   6   -1  1   3
^i      ^

-1  6   3   1   3
^           ^i

1   6   3   -1  3
    ^

res=2

4   3   1   2
^i          ^

2   3   1   4
^i  ^

3   2   1   4
^i      ^

1   2   3   4
                ^i

rea=5

*/