//Binary Search O(log(sum - max) * n) O(1), sum=nums總和, max=nums中最大值, n=nums.size()
class Solution410 {
public:
    int splitArray(vector<int>& nums, int k) {
        int l = 0, r = 0;
        for (int num : nums) {
            l = max(l, num);
            r += num;
        }

        while (l < r) {
            int mid = l + (r - l) / 2;
            int array_size = calArraySize(nums, mid);
            if (array_size > k) { // subarray數量太多, 增加每一個array的sum size
                l = mid + 1;
            } else { // array_size <= k, subarray數量太少, 減少一個array的sum
                     // size
                r = mid;
            }
        }
        return l;
    }

    int calArraySize(vector<int>& nums, int max_sum) {
        int size = 1;
        int sum = 0;
        for (int num : nums) {
            if (sum + num > max_sum) {
                ++size;
                sum = num;
            } else {
                sum += num;
            }
        }
        return size;
    }
};
/* ex : nums = [1,2,3,4,5] , k = 2

極端情況下,
k = 1, 則只能區分一塊, 即全總和15, sum = 15
k = 5, 全部拆開, 最小也只能是5, sum = 5
所以答案在[5, 15] 之間, l = 5, r = 15

題目想問最小化情況下, spilt區間的最大值
max sum     5   6   7   8   9   10  11  12  ...15
array size  4   3   3   3   2   2   2   2      1
                            ^res

求minimized largest sum of the split, 求左界
res = 9
*/