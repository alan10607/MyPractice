//Binary Search O(log(m + n)) O(log(m + n))
class Solution4 {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int len = nums1.size() + nums2.size();
        if(len % 2 == 1){//i=nums1's index, j=nums2's index
            return findKth(0, 0, len / 2 + 1, nums1, nums2);
        }else{
            return (findKth(0, 0, len / 2, nums1, nums2) + findKth(0, 0, len / 2 + 1, nums1, nums2)) / 2.0;
        }
    }
    int findKth(int i, int j, int k, vector<int>& nums1, vector<int>& nums2) {
        if(i >= nums1.size()) return nums2[j + k - 1];//換到陣列時要-1
        if(j >= nums2.size()) return nums1[i + k - 1];
        if(k == 1) return min(nums1[i], nums2[j]);

        int half = k / 2;
        int n1 = i + half - 1 < nums1.size() ? nums1[i + half - 1] : INT_MAX;
        int n2 = j + half - 1 < nums2.size() ? nums2[j + half - 1] : INT_MAX;
        if(n1 < n2){
            return findKth(i + half, j, k - half, nums1, nums2);
        }else{
            return findKth(i, j + half, k - half, nums1, nums2);
        }
    }
};