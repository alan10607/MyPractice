//Binary Search O(n) O(logn)
class Solution215 {
public:
    int findKthLargest(vector<int>& nums, int k) {
        return quickSort(0, nums.size() - 1, k - 1, nums);//k-1轉為位置
    }

    int quickSort(int i, int j, int k, vector<int>& nums){
        int p = i;//左為pivot, 大排到小
        int l = i + 1;
        int r = j;
        while(l <= r){
            if(nums[l] >= nums[p]){
                ++l;
            }else if(nums[r] <= nums[p]){
                --r;
            }else{
                swap(nums[l], nums[r]);
            }
        }
        swap(nums[p], nums[r]);//此時r會較大, 移到左邊

        if(r == k) return nums[r];
        if(r > k){
            return quickSort(i, r - 1, k, nums);
        }else{//r < k
            return quickSort(r + 1, j, k, nums);
        }
    }
};