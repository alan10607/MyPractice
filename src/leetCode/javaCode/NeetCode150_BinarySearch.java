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
    class Solution {
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

}