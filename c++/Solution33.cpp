//Binary Search O(logn) O(1)
class Solution33 {
public:
    int search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            
            if(nums[mid] == target) return mid;

            if(nums[mid] > nums[r]){//在左側, 不能使用r判斷大小
                if(nums[l] <= target && target < nums[mid]){//大-target-mid-最大-小
                    r = mid - 1;
                }else{//大-mid-target-最大-小 or 大-mid-最大-target-小
                    l = mid + 1;
                }
            }else{//在右側
                if(nums[mid] < target && target <= nums[r]){//大-最大-mid-target-小 
                    l = mid + 1;
                }else{//大-最大-target-mid-小 or 大-target-最大-mid-小
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
};