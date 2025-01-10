//Binary Search O(n) O(logn)
class Solution215 {
public:
    int findKthLargest(vector<int>& nums, int k) {
        return quickSelect(nums, 0, nums.size() - 1, k - 1); // k-1轉為位置
    }

    int quickSelect(vector<int>& nums, int start, int end, int k) {
        int pivit = nums[start]; // 左為pivot, 大排到小
        int l = start + 1;
        int r = end;
        while (l <= r) { 
            if (nums[l] < pivit && pivit < nums[r]) { // 這裡用if-else會TLE
                swap(nums[l++], nums[r--]);
            }
            if (nums[l] >= pivit) {
                ++l;
            }
            if (nums[r] <= pivit) {
                --r;
            }
        }
        swap(nums[start], nums[r]); // 此時r會較大, 移到左邊

        if (k == r) {
            return nums[r];
        } else if (k < r) {
            return quickSelect(nums, start, r - 1, k);
        } else { // k > l
            return quickSelect(nums, r + 1, end, k);
        }
    }
};


//Heap O(nlogn) O(k), n = nums.size()
class Solution215_2 {
public:
    int findKthLargest(vector<int>& nums, int k) { // 用pq會花比較多時間
        priority_queue<int, vector<int>, greater<int>> pq; // 小到大, min heap
        for (int num : nums) {
            pq.push(num);
            if (pq.size() > k) {
                pq.pop();
            }
        }
        return pq.top();
    }
};