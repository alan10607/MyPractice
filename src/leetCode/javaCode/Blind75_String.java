package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_String {

    //Time Complexity: O(n), Space Complexity: O(z), z為記憶用的字符集, 依題目為ASCII碼即最多128
    class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            //使用滑動窗口
            Set<Character> remember = new HashSet<Character>();
            int left = 0;
            int right = 0;
            int max = 0;

            while(right < s.length()){
                if(!remember.contains(s.charAt(right))){
                    //如果不存在, 就記起來, 窗口向右
                    remember.add(s.charAt(right));
                    max = Math.max(max, remember.size());
                    right++;
                }else{
                    //若有已存在, 則重複, 去掉最左指標的那個Character
                    remember.remove(s.charAt(left));
                    left++;
                }
            }

            return max;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(z), z為字符集, 最多26
    class Solution424 {
        public int characterReplacement(String s, int k) {
            //使用滑動窗口, 題目限定只有大寫字母
            int[] count = new int[26];
            int l = 0;
            int maxChar = 0;//單一字母的最大值
            int res = 0;

            for(int r=0; r<s.length(); r++){
                count[s.charAt(r) - 'A']++;//記得是大寫A
                maxChar = Math.max(maxChar, count[s.charAt(r) - 'A']);//紀錄單一字母的最大值

                //計算有無超過容許量, 最大長度 - 最多字母數 = 須被替換的字母數
                //可以用if的原因是因為只差1, l++後可以確定不會再進到迴圈
                if((r - l + 1) - maxChar > k){
                    //左移窗格
                    count[s.charAt(l) - 'A']--;
                    l++;
                }

                res = Math.max(res, r - l + 1);//記錄歷史最大長度
            }

            return res;

        /* s = "ABACD", k = 1
            l = 0, r = 0, remember = [1, 0, 0, 0, ...], 長度(r - l + 1) = 1, maxChar =1
            l = 0, r = 1, remember = [1, 1, 0, 0, ...], 長度 = 2, maxChar = 2
            l = 0, r = 2, remember = [2, 1, 0, 0, ...], 長度 = 3, maxChar = 2

            l = 0, r = 3, remember = [2, 1, 1, 0, ...], 長度 = 4, maxChar = 2, 左邊需移動
        =>  l = 1, r = 3, remember = [1, 1, 1, 0, ...], 長度 = 3, maxChar = 2,

            l = 1, r = 4, remember = [1, 1, 1, 1, ...], 長度 = 4, maxChar = 2, 左邊需移動
        =>  l = 2, r = 4, remember = [1, 0, 1, 1, ...], 長度 = 3, maxChar = 2,
        */
        }
    }

}