package leetCode.java;

//Binary Search O(log(m + n)) O(log(m + n))
class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if(len % 2 == 1){
            return findKth(0, 0, len / 2 + 1, nums1, nums2);//k帶入第幾個, 不是陣列位置
        }else{
            return (findKth(0, 0, len / 2, nums1, nums2) + findKth(0, 0, len / 2 + 1, nums1, nums2)) / 2.0;
        }
    }

    public int findKth(int i, int j, int k, int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        if(i >= m) return nums2[j + k - 1];//nums1為空
        if(j >= n) return nums1[i + k - 1];//nums2為空
        if(k == 1) return Math.min(nums1[i], nums2[j]);//剩一個可選

        int half = k / 2;//記得轉為陣列位置要-1
        int val1 = i + half - 1 < m ? nums1[i + half - 1] : Integer.MAX_VALUE;
        int val2 = j + half - 1 < n ? nums2[j + half - 1] : Integer.MAX_VALUE;
        if(val1 < val2){//比較中間數, 跳過比較小的那個
            return findKth(i + half, j, k - half, nums1, nums2);
        }else{
            return findKth(i, j + half, k - half, nums1, nums2);
        }
    }
}
/* find 3rd
1245
^
23
^
k=3 3/2=1

1245
 ^
23
^
k=2 2/2=1

1245
  ^
23
^
k=1 return min(4,2)=2 第3大為2

跳過情況: find 4th
124568
^
3
^
k=4 4/2=2 略過nums2因為超過長度

124568
  ^
3
^
k=2 2/2=1

124568
  ^
3
 ^
nums2為空, return 4 第4大為4
*/