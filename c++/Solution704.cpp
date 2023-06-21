//Binary Search O(logn) O(1)
class Solution704 {
public:
    int search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            if(target == nums[mid]) return mid;
            if(target < nums[mid]){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return -1;
    }
};