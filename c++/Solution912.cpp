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

    void quickSort(vector<int>& nums, int start, int end) { // 要用隨機pivot才會pass
        if (start >= end) return;

        int p = start + (rand() % (end + 1 - start)); // [start, end]範圍的隨機數
        int pivot = nums[p];
        swap(nums[start], nums[p]); // 先放到左邊待命, 結束while再換回來

        int l = start + 1;
        int r = end;
        while (l <= r) {// r = mid - 1的版本, 要是<=
            if (nums[l] < pivot) { // 二路快排
                ++l;
            } else if (nums[r] > pivot) {
                --r;
            } else { // nums[l] >= pivot && pivot >= nums[r]
                swap(nums[l++], nums[r--]);
            }
        }
        swap(nums[start], nums[r]);

        quickSort(nums, start, r - 1);
        quickSort(nums, r + 1, end);
    }
};
/*
為什麼是swap(nums[l], nums[r])?

因為跑完while時, nums是
p   小  小  ... 小  大  大  ...
                r  l

p跟r交換:
小   小  小  ... p  大  大  ...

p跟ㄠ交換:
大   小  小  ... 小  p  大  ...

大的跑道最左邊了, 這樣是錯的
*/