package leetCode.java;

//Binary Search O(n) O(logn), 快排時間複雜度為nlogn, 此處只排單邊, 空間複雜度為遞迴期望值
class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        //must solve it in O(n) time complexity
        return quickFind(0, nums.length - 1, k - 1, nums);//記得k要-1, 原k是第幾個
    }

    public int quickFind(int i, int j, int k, int[] nums){
        int p = i;//左側為pivot
        int l = i + 1;
        int r = j;
        //大排到小
        while(l <= r){
            if(nums[l] >= nums[p]){
                l++;
            }else if(nums[r] <= nums[p]){
                r--;
            }else{
                swap(l, r, nums);
            }
        }

        swap(p, r, nums);

        if(k == r){//此時r為pivot
            return nums[r];
        }else if(k < r){
            return quickFind(i, r - 1, k, nums);
        }else{//r > k
            return quickFind(r + 1, j, k, nums);
        }
    }

    public void swap(int a, int b, int[] nums){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
/* 大排到小 k=2
3 4 5 1 2
p l     r

3 4 5 1 2
p   r l   r交換pivot

5 4 3 1 2
--- - ---

5 4
p l
  r

5 4
p l
r   r交換pivot

5 4
- -

4
p l
r   r交換pivot

4
-   return 4
*/