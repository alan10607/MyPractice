package leetCode.javaCode;

import java.util.*;

/**
 *  Binary Search
 */
public class NeetCode150_BinarySearch {

    //Time Complexity: O(logn), Space Complexity: O(1)
    //Binary search
    class Solution704 {
        public int search(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            //有可能會相等
            while(l <= r){
                int mid = (l + r) / 2;
                if(nums[mid] == target) return mid;
                if(nums[mid] < target) l = mid + 1;
                if(nums[mid] > target) r = mid - 1;
            }
            return -1;
        }
    }

    //Time Complexity: O(log(mn)), Space Complexity: O(1), logm + logn = log(mn)
    //Binary search
    class Solution74 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int t = 0;
            int b = matrix.length - 1;
            int l = 0;
            int r = matrix[0].length - 1;

            int row = 0;
            while(t <= b){
                row = (t + b) / 2;
                if(target < matrix[row][l]){
                    b = row - 1;
                }else if(matrix[row][r] < target){
                    t = row + 1;
                }else{//matrix[row][i] <= target <= matrix[row][matrix[0].length - 1]
                    break;
                }
            }

            int mid = 0;
            while(l <= r){
                mid = (l + r) / 2;
                if(target < matrix[row][mid]){
                    r = mid - 1;
                }else if(matrix[row][mid] < target){
                    l = mid + 1;
                }else{
                    break;
                }
            }
            return matrix[row][mid] == target;
        }
    }

    //Time Complexity: O(m logn), Space Complexity: O(1), n = max(piles), m = piles.length
    //Binary search
    class Solution875 {
        public int minEatingSpeed(int[] piles, int h) {
            //可能的速度為每小時吃1 ~ max(piles)個香蕉, 透過二分法
            int l = 1;//至少會有1
            int r = 0;
            for(int pile : piles)
                r = Math.max(r, pile);

            int res = r;
            while(l <= r){
                int mid = l + (r - l) / 2;
                long hour = calHour(piles, mid);

                if(hour <= h){
                    res = Math.min(res, mid);//滿足條件就紀錄
                    r = mid - 1;//減慢
                }else{
                    l = mid + 1;//加快
                }
            }
            return res;
        }

        public long calHour(int[] piles, int speed){
            long hour = 0;//用long否則測試會超過
            for(int pile : piles)
                hour += pile / speed + (pile % speed == 0 ? 0 : 1);//無條件進位

            return hour;
        }
    }

    //Time Complexity: 初始化與set(): O(1), get(): O(logn), Space Complexity: O(n), n為儲存的數目
    //Binary search
    class Solution981 {
        class TimeMap {
            //All the timestamps timestamp of set are strictly increasing.
            public Map<String, List<Object[]>> map;

            public TimeMap() {
                map = new HashMap<>();//<key, [[time, value], ...]>
            }

            public void set(String key, String value, int timestamp) {
                if (!map.containsKey(key))
                    map.put(key, new ArrayList<Object[]>());

                map.get(key).add(new Object[]{timestamp, value});
            }

            public String get(String key, int timestamp) {
                if (map.containsKey(key)) {
                    return binarySearch(map.get(key), timestamp);
                }
                return "";
            }

            private String binarySearch(List<Object[]> timeList, int timestamp) {
                int l = 0;
                int r = timeList.size() - 1;
                String returnStr = "";
                while (l <= r) {
                    int mid = (l + r) / 2;
                    int t = (int) timeList.get(mid)[0];

                    if (t == timestamp) return (String) timeList.get(mid)[1];//一樣就直接回傳

                    if (t < timestamp) {
                        l = mid + 1;
                        returnStr = (String) timeList.get(mid)[1];//找到最近的下方
                    } else {
                        r = mid - 1;
                    }
                }
                return returnStr;
            }
        }
    }

    //Time Complexity: log(min(m,n)), Space Complexity: O(1), m = nums1.length, n = nums2.length
    //Binary search
    class Solution4 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len = nums1.length + nums2.length;
            if(len % 2 == 0){
                return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
            }else{
                return findKth(nums1, 0, nums2, 0, len / 2 + 1);
            }
        }

        public int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
            int m = nums1.length;
            int n = nums2.length;
            if (i >= m) return nums2[j + k - 1];//nums1為空
            if (j >= n) return nums1[i + k - 1];//nums2為空
            if (k == 1) return Math.min(nums1[i], nums2[j]);//正常情況

            //若超過範圍, 則設為最大值直接跳過, 不可能同時超過
            int half = k / 2;
            int val1 = i + half - 1 < m ? nums1[i + half - 1] : Integer.MAX_VALUE;
            int val2 = j + half - 1 < n ? nums2[j + half - 1] : Integer.MAX_VALUE;
            if (val1 < val2) {
                return findKth(nums1, i + half, nums2, j, k - half);
            } else {
                return findKth(nums1, i, nums2, j + half, k - half);
            }
        }
        /*
        正常情況:
        145
        ^
        23
        ^
        k=3, 3/2=1

        145
         ^
        23
        ^
        k=2, 2/2=1

        145
         ^
        23
         ^
        k=1, return 3

        跳過某數列的情況:
        124567
        ^
        3
        ^
        k=4, 4/2=2, 2<nums2.len, 跳過nums2

        124567
          ^
        3
        ^
        k=2 2/2=1

        124567
          ^
        3
         ^
        k=1, return 4
        */
    }

}