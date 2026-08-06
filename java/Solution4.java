package leetCode.java;

//Binary Search O(log(m + n)) O(log(m + n))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1); // 保持 nums1較短
        }

        int len = nums1.length + nums2.length;
        int half = len / 2;
        int l = 0, r = nums1.length; // 只在nums1上做binary search, 注意是r=n因為有可能全部都分割到左邊
        while (l <= r) {
            int i = l + (r - l) / 2; // nums1分割成[0,i-1][i,n-1]
            int j = half - i; // nums2分割成[0,j-1][j,m-1]
            int l1 = i > 0 ? nums1[i - 1] : Integer.MIN_VALUE; // i==0, 則左側為空, 全在右
            int r1 = i < nums1.length ? nums1[i] : Integer.MAX_VALUE; //i==n, 則右側為空, 全在左
            int l2 = j > 0 ? nums2[j - 1] : Integer.MIN_VALUE;
            int r2 = j < nums2.length ? nums2[j] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                return len % 2 == 0 ? 
                    (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0 : // 偶數則res=(最大的左側/最小的右側)/2
                    Math.min(r1, r2); // 奇數則res=最小的右側, 因為右側是較長邊
            } else if (l2 > r1) {
                l = i + 1;
            } else { // l1 > r2
                r = i - 1;
            }
        }
        return -1;
    }
}


//Binary Search O(log(m + n)) O(log(m + n))
class Solution4_2 {
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