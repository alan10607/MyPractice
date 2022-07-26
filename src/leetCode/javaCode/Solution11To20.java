package leetCode.javaCode;

import java.util.*;

public class Solution11To20 {

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution11 {
        public int maxArea(int[] height) {
            //經典左右雙指標
            int left = 0;
            int right = height.length - 1;
            int max = 0;
            while(left < right){
                int size = Math.min(height[left], height[right]) * (right - left);
                if(max < size)
                    max = size;
                if(height[left] < height[right]){
                    left++;
                }else{
                    right--;
                }
            }

            return max;
        }
    }

    //Time Complexity: O(logn), Space Complexity: O(1)
    class Solution12 {
        public String intToRoman(int num) {
            //1 <= num <= 3999
            Map<Integer, String> roman = new HashMap<Integer, String>();
            roman.put(1000, "M");
            roman.put(900, "CM");
            roman.put(500, "D");
            roman.put(400, "CD");
            roman.put(100, "C");
            roman.put(90, "XC");
            roman.put(50, "L");
            roman.put(40, "XL");
            roman.put(10, "X");
            roman.put(9, "IX");
            roman.put(5, "V");
            roman.put(4, "IV");
            roman.put(1, "I");
            StringBuffer sb = new StringBuffer();

            for(int exp = 1000; exp > 0; exp /= 10){
                int digit = num / exp;
                if(digit >= 1 && digit <=3){
                    for(int i=0; i<digit; i++)
                        sb.append(roman.get(exp));
                }else if(digit == 4){
                    sb.append(roman.get(exp * 4));
                }else if(digit == 5){
                    sb.append(roman.get(exp * 5));
                }else if(digit >= 6 && digit <=8){
                    sb.append(roman.get(exp * 5));
                    for(int i = 0; i < digit - 5; i++)
                        sb.append(roman.get(exp));
                }else if(digit == 9){
                    sb.append(roman.get(exp * 9));
                }

                num = num % exp;
            }

            return sb.toString();
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution13s {
        public int romanToInt(String s) {
            //1 <= num <= 3999
            Map<Character, Integer> roman = new HashMap<Character, Integer>();
            roman.put('M', 1000);
            roman.put('D', 500);
            roman.put('C', 100);
            roman.put('L', 50);
            roman.put('X', 10);
            roman.put('V', 5);
            roman.put('I', 1);

            int num = 0;
            int lastRomanSize = 0;
            for(int i=0; i<s.length(); i++){
                //9 or 4的情況
                if(lastRomanSize < roman.get(s.charAt(i))){
                    num = num - lastRomanSize + roman.get(s.charAt(i)) - lastRomanSize;
                }else{
                    num += roman.get(s.charAt(i));
                }

                lastRomanSize = roman.get(s.charAt(i));
            }

            return num;
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(1), m為字串個數, n為字串平均長度
    class Solution14 {
        public String longestCommonPrefix(String[] strs) {
            for(int i=0; i<strs[0].length(); i++){
                char temp = strs[0].charAt(i);
                for(int j=1; j<strs.length; j++){
                    //不可以用直接在array前面用!, if(!strs[j].charAt(i) == temp)是錯誤的
                    if(i < strs[j].length() && strs[j].charAt(i) == temp){
                        continue;
                    }else{
                        return strs[0].substring(0, i);
                    }
                }
            }
            return strs[0];
        }
    }

    //Time Complexity: O(n^2), Space Complexity: O(n), 空間複雜度主要是排序, 可能為O(n)
    class Solution15 {
        public List<List<Integer>> threeSum(int[] nums) {
            //用三指標

            //要先sort
            Arrays.sort(nums);//快速或合併排序 O(n logn);
            List<List<Integer>> result = new ArrayList<List<Integer>>();

            //!!要減2 因為為右邊已經有兩個指標
            for(int i=0; i<nums.length - 2; i++){
                //重複上一個，跳過
                if(i > 0 && nums[i] == nums[i - 1])
                    continue;

                int left = i + 1;
                int right = nums.length - 1;

                while(left < right){
                    if(left > i + 1 && nums[left] == nums[left - 1]){
                        left++;
                        continue;
                    }
                    if(right < nums.length - 1 && nums[right] == nums[right + 1]){
                        right--;
                        continue;
                    }


                    if(nums[i] + nums[left] + nums[right] == 0){
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        result.add(temp);
                    }

                    if(nums[i] + nums[left] + nums[right] < 0){
                        left++;
                    }else{
                        right--;
                    }
                }
            }

            return result;
        }
    }

    //Time Complexity: O(n^2), Space Complexity: O(logn), 空間複雜度主要是排序, 可能為O(logn)
    class Solution16 {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int result = 0;
            int gap = Integer.MAX_VALUE;//差距 !!如果是Math.abs(Integer.MIN_VALUE)會是回-Integer.MIN_VALUE
            for(int i=0; i<nums.length - 2; i++){
                if(i > 0 && nums[i] == nums[i - 1])
                    continue;


                int left = i + 1;
                int right = nums.length - 1;

                while(left < right){
                    if(left > i + 1 && nums[left] == nums[left - 1]){
                        left++;
                        continue;
                    }
                    if(right < nums.length - 1 && nums[right] == nums[right + 1]){
                        right--;
                        continue;
                    }

                    int sum = nums[i] + nums[left] + nums[right];


                    if(target == sum)
                        return target;

                    if(Math.abs(target - sum) < gap) {
                        gap = Math.abs(target - sum);
                        result = sum;
                    }

                    if(sum < target){
                        left++;
                    }else{
                        right--;
                    }
                }

            }

            return result;
        }
    }

    //Time Complexity: O(3^m 4^n), Space Complexity: O(m + n), m為3個英文的數字(2, 3, 4, 5, 6, 8), n為4個英文的數字(7, 9)
    class Solution17 {
        public List<String> letterCombinations(String digits) {
            String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            List<String> result = new ArrayList<String>();

            //設定例外
            if(digits.length() > 0)
                phoneDFS(digits, phone, 0, "", result);

            return result;
        }

        /**
         * 使用DFS
         * @param digits 電話數字
         * @param phone 字母集
         * @param posi 現在的位子
         * @param temp 暫存字串
         * @param result 回傳結果
         */
        public void phoneDFS(String digits, String[] phone, int posi, String temp, List<String>result){
            //設定退回點
            if(posi == digits.length()){
                result.add(temp);
                return;
            }

            int num = Character.getNumericValue(digits.charAt(posi));
            if(num <= 1)//跳過這一次
                phoneDFS(digits, phone, posi + 1, temp, result);

            String eng = phone[num];
            for(int i=0; i<eng.length(); i++){
                phoneDFS(digits, phone, posi + 1, temp + eng.substring(i, i+1), result);
            }

        }
    }

    //Time Complexity: O(n^3), Space Complexity: O(logn), 空間複雜度主要是排序, 可能為O(logn)
    class Solution18 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            //要記得先排序
            Arrays.sort(nums);

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            for(int i=0; i < nums.length - 3; i++){
                if(i > 0 && nums[i] == nums[i - 1])
                    continue;

                for(int j = i + 1; j < nums.length - 2; j++){
                    if(j > i + 1 && nums[j] == nums[j - 1])
                        continue;

                    int left = j + 1;
                    int right = nums.length - 1;
                    while(left < right){
                        if(left > j + 1 && nums[left] == nums[left - 1]){
                            left++;
                            continue;
                        }
                        if(right < nums.length - 1 && nums[right] == nums[right + 1]){
                            right--;
                            continue;
                        }

                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if(sum == target){
                            List<Integer> temp = new ArrayList<Integer>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[left]);
                            temp.add(nums[right]);
                            result.add(temp);
                        }

                        if(sum < target){
                            left++;
                        }else{
                            right--;
                        }
                    }


                }
            }

            return result;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1), n為鏈表長度
    class Solution19 {
        class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            //用快慢雙指針
            //必須創建一個dummy在head之前, 否則head.next = null時無法得到head.next.next會null pointer exception
            ListNode dummy = new ListNode(0, head);
            ListNode fast = head;
            ListNode slow = dummy;

            //讓快指針先走n個, 當快指針走完時, 慢指針剛好走到倒數第n個
            for(int i=0; i<n; i++)
                fast = fast.next;

            while(fast != null){
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;

            return dummy.next;

        }
    }

    //Time Complexity: O(n), Space Complexity: O(n + z), n = s' length, z = haspMap' size(6)
    class Solution20 {
        public boolean isValid(String s) {
            Map<Character, Character> bar = new HashMap<Character, Character>();
            bar.put(')', '(');
            bar.put(']', '[');
            bar.put('}', '{');

            //dequeue是指double-ended queue(雙向佇列)
            //使用Deque比Stack更好, 除了提供更多進出方法, Deque是interface比Stack是class有更多彈性
            Deque<Character> stack = new LinkedList<Character>();

            for(int i=0; i<s.length(); i++){
                if(bar.get(s.charAt(i)) != null
                        && stack.peek() == bar.get(s.charAt(i))){
                    stack.pop();
                }else{
                    stack.push(s.charAt(i));
                }
            }

            return stack.isEmpty();
        }
    }

}