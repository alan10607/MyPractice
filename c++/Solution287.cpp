//Fast & Slow Pointer Floyd Cycle Detection Algorithm O(n) O(1)
class Solution287 {
public:
    int findDuplicate(vector<int>& nums) {
        int fast = 0, slow = 0;
        do{
            fast = nums[nums[fast]];
            slow = nums[slow];
        }while(fast != slow);

        int start = 0;
        while(start != fast){
            start = nums[start];
            fast = nums[fast];
        }
        return start;
    }
};
/* nums = [1,3,4,2,2]
0   1
1   3
2   4
3   2
4   2

0->1->3->2->4
         ^  |
         ----
*/