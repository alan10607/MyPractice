package leetCode.javaCode;

import java.util.*;

public class Solution1To10 {

    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> remember = new HashMap<Integer, Integer>();
            for(int i=0; i<nums.length; i++){
                if(remember.containsKey(target - nums[i])){
                    return new int[]{remember.get(target - nums[i]), i};
                }else{
                    remember.put(nums[i], i);
                }
            }

            return new int[]{};
        }
    }

    class Solution2 {
        public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode root = new ListNode();
            ListNode temp = root;
            int carry = 0;
            while(l1 != null || l2 != null){
                int v1 = l1 != null ? l1.val : 0;
                int v2 = l2 != null ? l2.val : 0;
                int sum = v1 + v2 + carry;
                carry = sum / 10;

                temp.next = new ListNode(sum % 10);
                temp = temp.next;

                if(l1 != null)
                    l1 = l1.next;

                if(l2 != null)
                    l2 = l2.next;
            }

            //if have carry, than one more position
            if(carry > 0){
                temp.next = new ListNode(carry);
            }

            return root.next;
        }
    }

    class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> readed = new HashSet<Character>();
            int left = 0;
            int right = 0;
            int max = 0;
            while(right < s.length()){
                if(readed.contains(s.charAt(right))){
                    max = Math.max(max, right - left);
                    readed.remove(s.charAt(left));
                    left++;
                }else{
                    readed.add(s.charAt(right));
                    right++;
                }
            }

            max = Math.max(max, right - left);

            return max;
        }
    }

    class Solution4 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len = nums1.length + nums2.length;
            if(len % 2 == 0){
                return (findKth(nums1, nums2, len / 2) + findKth(nums1, nums2, len / 2 + 1)) / 2.0;
            }else{
                return findKth(nums1, nums2, len / 2 + 1);
            }
        }

        //k start from 1
        public int findKth(int[] nums1, int[] nums2, int k){
            //跳出條件
            if(nums1.length == 0) return nums2[k - 1];
            if(nums2.length == 0) return nums1[k - 1];
            if(k == 1) return nums1[0] < nums2[0] ? nums1[0] : nums2[0];

            //使用二分法
            int half = k / 2;
            int mid1 = half > nums1.length ? nums1.length : half;
            int mid2 = half > nums2.length ? nums2.length : half;

            //記得位子要是長度 - 1
            if(nums1[mid1 - 1] > nums2[mid2 - 1]){
                //刪除nums2後半, k要減掉以符合原本的k
                return findKth(nums1, Arrays.copyOfRange(nums2, mid2, nums2.length), k - mid2);
            }else{
                //刪除nums1後半, k要減掉以符合原本的k
                return findKth(Arrays.copyOfRange(nums1, mid1, nums1.length), nums2, k - mid1);
            }
        }

    }

    class Solution5 {
        public String longestPalindrome(String s) {
            int len = s.length();
            boolean[][] dp = new boolean[len][len];
            int max = 0;
            int start = 0;
            int end = 0;

            //建立一個動態規劃Dynamic programming
            //dp[i][j]表示從i到j是否是對稱字串

            //j在右邊, 故較大
            for(int j=0; j<len; j++){
                dp[j][j] = true;

                //i在左邊部會超過j
                for(int i=0; i<j; i++){
                    if(s.charAt(i) == s.charAt(j)
                            && ((j - i) <= 2 || dp[i + 1][j - 1] == true)){
                        //兩個字一樣而且是aa這種或是內部是對稱
                        dp[i][j] = true;

                        int thisLen = j - i + 1;
                        if(thisLen > max){
                            max = thisLen;
                            start = i;
                            end = j;
                        }
                    }
                }
            }

            return s.substring(start, end + 1);
        }
    }

    class Solution6 {
        public String convert(String s, int numRows) {
            //設定例外
            if(numRows == 1)
                return s;

            StringBuffer[] sbArr = new StringBuffer[numRows];
            StringBuffer result = new StringBuffer();
            int row = 0;
            int direction = 1;

            //必須要對物件初始化
            //!!使用編譯器蜜糖for loop會是沒有效果的
            for(int i=0; i<sbArr.length; i++){
                sbArr[i] = new StringBuffer();
            }

            for(int i=0; i<s.length(); i++){
                sbArr[row].append(s.charAt(i));

                row += direction;
                if((direction == 1 && row == numRows - 1)
                        || (direction == -1 && row == 0)){
                    direction = -direction;
                }
            }

            for(StringBuffer sb : sbArr){
                result.append(sb);
            }

            return result.toString();

        }
        /*
        0  6  0 4
        1 57  135
        24 8  2 6
        3  9
        */
    }

    class Solution7 {
        public int reverse(int x) {
            int result = 0;
            int num = x > 0? x : -x;
            while(num > 0){
                int last = num % 10;
                //Integer.MAX_VALUE = 2147483647 Integer.MIN_VALUE= -2147483648
                //正數到0距離較小，故判斷正數就夠了
                if(Integer.MAX_VALUE / 10 < result)
                    return 0;

                result = result * 10 + last;
                num /= 10;
            }
            return x < 0 ? -result : result;
        }
    }

    class Solution8 {
        public int myAtoi(String s) {
            int result = 0;
            int nega = 1;
            s = s.trim();

            //例外條件, 否則charAt(0)
            if(s.length() == 0){
                return 0;
            }

            //判斷正或負, 用else if指判斷一次
            if(s.charAt(0) == '-'){
                nega = -1;
                s = s.substring(1);
            }else if(s.charAt(0) == '+'){
                s = s.substring(1);
            }

            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    int last = Character.getNumericValue(s.charAt(i));

                    //設定超出邊界
                    if(result > Integer.MAX_VALUE / 10 //十位數以上
                            || (result == Integer.MAX_VALUE / 10 && nega == 1 && last >= Integer.MAX_VALUE % 10) //正數 個位數
                            || (result == Integer.MAX_VALUE / 10 && nega == -1 && last >= Integer.MAX_VALUE % 10 + 1)){//負數 個位數
                        return nega == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }

                    result = result * 10 + last;
                }else{
                    break;
                }
            }

            return result * nega;
        }
    }

    class Solution9 {
        public boolean isPalindrome(int x) {
            int reverse = 0;
            int temp = x;
            while(temp > 0){
                reverse = reverse * 10 + temp % 10;
                temp /= 10;
            }
            return x == reverse;
        }
    }

    class Solution10 {
        public boolean isMatch(String s, String p) {
            //如果檢核結束, 如果檢核完判斷是否有剩下未判斷的s
            if (p.isEmpty())
                return s.isEmpty();

            //使用遞迴法解所有可能
            if(p.length() > 1 && p.charAt(1) == '*'){
                return isMatch(s, p.substring(2))//跳過這次的情況
                        || (!s.isEmpty()//其他照舊
                        && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))
                        && isMatch(s.substring(1), p));//p不變
            }else{
                return !s.isEmpty()//s還有但已無檢核的p, 則為錯
                        && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))//判斷這次
                        && isMatch(s.substring(1), p.substring(1));//後續用遞迴
            }
        }
    }

}