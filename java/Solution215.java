package leetCode.java;

//Binary Search O(n) O(logn), 快排時間複雜度為nlogn, 此處只排單邊, 空間複雜度為遞迴期望值
class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        // Can you solve it without sorting? -> quickSelect
        return quickSelect(0, nums.length - 1, nums, k - 1); // 記得轉index, 原k是第幾個
    }

    public int quickSelect(int start, int end, int[] nums, int index) {
        int pivot = nums[start];
        int l = start + 1;
        int r = end;

        // 由大到小排列
        while (l <= r) {
            if (nums[l] > pivot) {
                ++l;
            } else if (nums[r] < pivot) {
                --r;
            } else {
                swap(l++, r--, nums); // 記得要移動
            }
        }

        swap(start, r, nums);

        if (index == r) {
            return nums[r];
        } else if (index < r) {
            return quickSelect(start, r - 1, nums, index);
        } else { // index > r
            return quickSelect(r + 1, end, nums, index);
        }
    }

    public void swap(int a, int b, int[] nums) {
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


//Heap O(nlogn) O(k), n = nums.size()
class Solution215_2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap, 小排到大
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) { // 拿掉最小的
                pq.poll();
            }
        }
        return pq.peek();
    }
}