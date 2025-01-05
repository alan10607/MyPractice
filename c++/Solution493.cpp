class Solution493 {
public:
    int reversePairs(vector<int>& nums) {
        return mergeSort(nums, 0, nums.size() -1);
    }

    int mergeSort(vector<int>& nums, int start, int end) {
        if (start >= end) return 0;

        int mid = (end - start) / 2 + start;
        int res = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);
        for (int i = start; i <= mid; ++i) {
            int j = mid + 1;
            while (j <= end && nums[i] / 2.0 > nums[j]) { // 用除的, 否則給INT_MAX會overflow
                ++j; // 可以嘗試找更大的j
            }
            res += j - (mid + 1);// 代表[mid + i, j)區間都滿足nums[i] > 2 * nums[j]
        }
        merge(nums, start, mid, mid + 1, end);
        return res;
    }

    void merge(vector<int>& nums, int start1, int end1, int start2, int end2){
        vector<int> tmp(end2 - start1 + 1);
        int i = 0, j = start1, k = start2;
        while (j <= end1 && k <= end2) {
            if (nums[j] < nums[k]) {
                tmp[i++] = nums[j++];
            } else {
                tmp[i++] = nums[k++];
            }
        }

        while (j <= end1) {
            tmp[i++] = nums[j++];
        }

        while (k <= end2) {
            tmp[i++] = nums[k++];
        }

        for (int idx = 0; idx < tmp.size(); ++idx) {
            nums[start1 + idx] = tmp[idx];
        }
    }
};