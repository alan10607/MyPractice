package leetCode.javaCode;

import java.util.*;

/**
 *  Arrays & Hashing
 *  Two Pointers
 *  Sliding Windows
 */
public class NeetCode150_Array {

    //Time Complexity: O(1), Space Complexity: O(1), 數獨大小為固定
    //Array
    class Solution36 {
        public boolean isValidSudoku(char[][] board) {
            int[][] row = new int[9][9];
            int[][] col = new int[9][9];
            int[][][] sub = new int[3][3][9];
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    if(board[i][j] != '.'){
                        int index = board[i][j] - '1';//從1開始
                        row[i][index]++;
                        col[j][index]++;
                        sub[i / 3][j / 3][index]++;
                        if(row[i][index] > 1 || col[j][index] > 1 || sub[i / 3][j / 3][index] > 1)
                            return false;
                    }
                }
            }
            return true;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    //LR Pointer
    class Solution167 {
        public int[] twoSum(int[] numbers, int target) {
            int l = 0;
            int r = numbers.length - 1;
            while(l < r){
                int sum = numbers[l] + numbers[r];
                if(sum == target)
                    return new int[]{l + 1, r + 1};//題目的index從1開始

                if(sum < target) l++;
                if(sum > target) r--;
            }
            return new int[]{};
        }
    }

    class Solution42 {
        //Time Complexity: O(n), Space Complexity: O(1)
        //LR Pointer
        public int trap(int[] height) {
            int l = 0;
            int r = height.length - 1;
            int leftMax = 0;
            int rightMax = 0;
            int res = 0;

            //既然高度是透過min(height[l], height[r])決定, 直接讓小的先走就好
            while(l < r){
                if(height[l] < height[r]){
                    leftMax = Math.max(leftMax, height[l]);
                    res += leftMax - height[l];//若height[l] < leftMax, 則不為0
                    l++;
                }else{
                    rightMax = Math.max(rightMax, height[r]);
                    res += rightMax - height[r];
                    r--;
                }
            }
            return res;
        }

        //Time Complexity: O(n), Space Complexity: O(n)
        //Stack
        public int trap2(int[] height) {
            Deque<Integer> deque = new LinkedList<>();//<位置>
            int res = 0;
            for(int i=0; i<height.length; i++){
                while(!deque.isEmpty() && height[deque.peek()] < height[i]){
                    int bottom = deque.poll();
                    if(deque.isEmpty())
                        break;//沒有左側可以比對了

                    //左右側選最小
                    int left = deque.peek();
                    res += (Math.min(height[left], height[i]) - height[bottom]) * (i - left - 1);
                }
                deque.push(i);
            }
            return res;
        }
        /* height = [3,2,0,0,2,5,4,5]
             * *
             ***
        *    ***
        **  ****
        **  ****
            ^poll=>0,0, res=4

             * *
             ***
        *    ***
        **..****
        **..****
             ^poll()=>2,2,3, res=8

             * *
             ***
        *....***
        **..****
        **..****
               ^poll=>4, res=9
        */
    }

    //Time Complexity: O(n), Space Complexity: O(z), n = s2.length(), z = 26
    //Slide window
    class Solution567 {
        public boolean checkInclusion(String s1, String s2) {
            //permutation表示可以重新排列, 但要在一起的單字
            if(s1.length() > s2.length()) return false;

            int[] counts = new int[26];
            int match = 0;//需要滿足的字母有幾種
            for(char ch : s1.toCharArray()){
                if(counts[ch - 'a']++ == 0) match++;//count時順便計算match
            }

            for(int i=0; i<s1.length(); i++){
                if(--counts[s2.charAt(i) - 'a'] == 0) match--;
            }

            if(match == 0) return true;//這裡也要判斷, 避免剛好是prefix的時候漏掉

            for(int i=s1.length(); i<s2.length(); i++){
                char rCh = s2.charAt(i);
                char lCh = s2.charAt(i - s1.length());

                if(--counts[rCh - 'a'] == 0) match--;//減到為0 => match--
                if(counts[lCh - 'a']++ == 0) match++;//原本為0 => match++
                if(match == 0) return true;
            }
            return false;
        }

        //Time Complexity: O(n), Space Complexity: O(z), n = s2.length(), z為s1的字符種類
        public boolean checkInclusion2(String s1, String s2) {
            //permutation表示可以重新排列, 但要在一起的單字
            if(s1.length() > s2.length()) return false;
            Map<Character, Integer> counts = new HashMap<>();
            for(char ch : s1.toCharArray())
                counts.put(ch, counts.getOrDefault(ch, 0) + 1);

            int l = 0;
            int r = 0;
            int okCount = 0;
            while(r < s2.length()){
                if(r - l + 1 > s1.length()){
                    char lCh = s2.charAt(l);
                    if(counts.containsKey(lCh)){
                        if(counts.get(lCh) == 0)
                            okCount--;
                        counts.put(lCh, counts.get(lCh) + 1);
                    }
                    l++;
                }

                char rCh = s2.charAt(r);
                if(counts.containsKey(rCh)){
                    counts.put(rCh, counts.get(rCh) - 1);
                    if(counts.get(rCh) == 0)
                        okCount++;
                }

                if(okCount == counts.size())
                    return true;

                r++;
            }

            return false;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(k), n = nums.length
    //Slide window
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            //依序查會Time Limit Exceeded
            Deque<Integer> deque = new LinkedList<>();//保持deque[0]為最大, 放入位置
            int[] res = new int[nums.length - k + 1];
            int l = 0;
            int r = 0;

            while(r < nums.length){
                //從右加入, 保持頭最大
                while(!deque.isEmpty() && nums[deque.peekLast()] < nums[r]){
                    deque.pollLast();
                }

                deque.offer(r);//放入位置

                //從左刪去超出範圍
                if(deque.peek() < l)
                    deque.poll();

                //開始記錄
                if(r + 1 >= k){
                    res[l] = nums[deque.peek()];//記得位置轉為數字
                    l++;
                }
                r++;
            }
            return res;
        }
        /*
        [8, 7, 6, 9]
        deque = [], offer(8)
        deque = [8], offer(7)
        deque = [8, 7], max[0] = peek() = 8, poll()
        deque = [7], offer(6)
        deque = [7, 6], max[1] = peek() = 7, poll()
        deque = [6], 要保持頭為最大, pollLast(), offer(9)
        deque = [9], max[2] = peek() = 9
        */
    }

}