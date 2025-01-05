//Merge Sort O(nlogn) O(n)
class Solution912 {
public:
    vector<int> sortArray(vector<int>& nums) {
        mergeSort(nums, 0, nums.size() - 1);
        return nums;
    }

    void mergeSort(vector<int>& nums, int start, int end) {
        if (start >= end) return;

        int mid = (end - start) / 2 + start;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, mid + 1, end);
    }

    void merge(vector<int>& nums, int start1, int end1, int start2, int end2) {
        vector<int> tmp(end2 - start1 + 1);
        int i = 0, j = start1, k = start2;
        while (j <= end1 && k <= end2) { // 把較小的先複製到tmp
            if (nums[j] < nums[k]) {
                tmp[i++] = nums[j++];
            } else { // nums[j] >= nums[k]
                tmp[i++] = nums[k++];
            }
        }

        while (j <= end1) {
            tmp[i++] = nums[j++];
        }

        while (k <= end2) {
            tmp[i++] = nums[k++];
        }

        for (int idx = 0; idx < tmp.size(); ++idx) { // 將tmp複製回原本array
            nums[start1 + idx] = tmp[idx];
        }
    }
};


// Quick Sort O(nlogn) O(n)
class Solution912_2 {
public:
    vector<int> sortArray(vector<int>& nums) {
        quickSort(nums, 0, nums.size() - 1);
        return nums;
    }

    void quickSort(vector<int>& nums, int start, int end) {
        if (start >= end) return;
        int p = start; // 左為pivot, 小排到大
        int l = start + 1;
        int r = end;
        while (l <= r) { // r = mid - 1的版本, 要是<=
            if (nums[l] <= nums[p]) {
                ++l;
            } else if (nums[r] >= nums[p]) {
                --r;
            } else { // nums[l] > nums[p] && nums[p] > nums[r]
                swap(nums[l], nums[r]);
                ++l;
                --r;
            }
        }
        swap(nums[p], nums[r]); // 此時r會在較小的那側(nums[r] < nums[p]), 跟pivot交換

        quickSort(nums, start, r - 1);
        quickSort(nums, r + 1, end);
    }
};