//Binary Search O(logn) O(1)
class Solution153 {
public:
    int findMin(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        while(l < r){
            int mid = (l + r) / 2;
            if(nums[mid] > nums[r]){
                l = mid + 1;//在左側
            }else{
                r = mid;//透過 /2變小
            }
        }
        return nums[r];
    }
};