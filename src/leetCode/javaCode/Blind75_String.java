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

    //Time Complexity: O((s + t) z), Space Complexity: O(z), z為字符集, 最多26
    //最壞情況s與t每個字母都要進一次回圈, 故(s + t) z
    class Solution76 {
        public String minWindow(String s, String t) {
            Map<Character, Integer> tMap = new HashMap<Character, Integer>();//<需要的字母, 數量>

            //初始化需要的字母
            for(char ch : t.toCharArray())
                tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);

            int l = 0;
            int need = tMap.size();//需要的字母
            int have = 0;//已經有的字母
            int[] res = new int[2];
            int resLen = Integer.MAX_VALUE;
            //開始滑動窗口
            for(int r=0; r<s.length(); r++){
                char ch = s.charAt(r);

                if(tMap.containsKey(ch)){
                    tMap.put(ch, tMap.get(ch) - 1);//紀錄目前讀到的單字

                    //檢查字母是不是已經滿足要求
                    if(tMap.get(ch).equals(0))
                        have++;
                }

                //滿足條件時, 左側向右移動
                while(have == need){
                    //1. 更新數據
                    if((r - l + 1) < resLen){
                        resLen = r - l + 1;
                        res[0] = l;
                        res[1] = r;
                    }

                    //2. 滑動左側
                    char leftCh = s.charAt(l);
                    if(tMap.containsKey(leftCh)){
                        tMap.put(leftCh, tMap.get(leftCh) + 1);

                        //再次檢查字母是不是不滿足要求
                        if(tMap.get(leftCh) > 0)
                            have--;
                    }

                    l++;
                }
            }

            //搜尋後, 答案長度必須要小於等於原本的s, 否則就是沒有查到
            return resLen <= s.length() ? s.substring(res[0], res[1] + 1) : "";
        }

        /* s = "ADOBECODEBANC", t = "ABC"

            ADOBECODEBANC   resLen = 6
            ^l   ^r

            ADOBECODEBANC   進入while
             ^l       ^r
            ADOBECODEBANC   resLen = 6
                 ^l   ^r

            ADOBECODEBANC   進入while
                  ^l    ^r
            ADOBECODEBANC   resLen = 4
                     ^l ^r
        */
    }

    //Time Complexity: O(n), Space Complexity: O(z), z為s與t的字符集, 最多26
    class Solution242 {
        public boolean isAnagram(String s, String t) {
            //判斷是不是重組字

            //設定例外
            if(s.length() != t.length())
                return false;

            Map<Character, Integer> remember = new HashMap<Character, Integer>();
            for(char ch : s.toCharArray()){
                remember.put(ch, remember.getOrDefault(ch, 0) + 1);
            }

            //開始配對s與t, 如果有一樣就刪掉
            for(char ch : t.toCharArray()){
                if(remember.containsKey(ch)){
                    remember.put(ch, remember.get(ch) - 1);
                    if(remember.get(ch).equals(0))
                        remember.remove(ch);
                }else{
                    return false;//之前沒出現過, 必不由同單字組成
                }
            }

            return remember.size() == 0;
        }
    }

    //Time Complexity: O(n(m + z)), Space Complexity: O(n(m + z))
    //z為字符集26個, k為最長的str長度, n = strs.length, 每次進入回圈都要k次紀錄hash, z次由hash轉成string(word)
    class Solution49 {
        public List<List<String>> groupAnagrams(String[] strs) {
            //本題運用char[]當作hash轉String儲存的方式比對
            Map<String, List<String>> memo = new HashMap<String, List<String>>();//<hash的文字, <同樣組合的單字...>>
            List<List<String>> res = new ArrayList<List<String>>();

            for(String str : strs){
                char[] hash = new char[26];//要建立的是char的array, 不是int
                for(char ch : str.toCharArray())
                    hash[ch - 'a']++;//由於只有小寫英文, 故第一個是'a' = 97

                String word = new String(hash);//建立hash需要O(z), z = 26字母
                if(!memo.containsKey(word))
                    memo.put(word, new ArrayList<String>());

                memo.get(word).add(str);
            }

            //map to list
            res.addAll(memo.values());//map的array轉為list輸出
            /* 相當於
            for(String word : memo.keySet())
                res.add(memo.get(word));
            */

            return res;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n + z), n = s.length(), z為括號字符集共6
    class Solution19 {
        public boolean isValid(String s) {
            //使用quene, dequeue是指double-ended queue(雙向佇列)
            //使用Deque比Stack更好, 除了提供更多進出方法, Deque是interface比Stack是class有更多彈性
            //Stack<Character> stack = new Stack<Character>();
            Deque<Character> quene = new LinkedList<Character>();
            Map<Character, Character> bar = new HashMap<Character, Character>();
            bar.put(')', '(');
            bar.put('}', '{');
            bar.put(']', '[');

            for(int i=0; i<s.length(); i++){
                if(bar.containsKey(s.charAt(i)) && quene.peek() == bar.get(s.charAt(i))){
                    quene.poll();
                }else{
                    quene.push(s.charAt(i));//從頭放進來
                }
            }

            return quene.size() == 0;
        }

        /*
                    vpeek
             [head] 0 1 2 3 4 [bot]
             *push=>         <=add*
              pop =>         <=offer*
             *poll=>         =>pollLast
            remove<=

        Deque 方法:
            push(E) - 在頭部加入元素
            add(E) - 在尾部加入元素
            offer(E) - 在尾部加入元素, 並返回一個boolean值來表示是否成功
            pop() - 刪除在頭部元素, 若為空則回Expection (=remove())
            poll() - 刪除在頭部元素, 若為空則回null (=peekFirst())
            pollLast - 刪除頭部元素, 若為空則回null
            peek() - 返回隊列的開頭, 若為空則回null (=peekFirst())
            peekLast() - 返回隊列的開頭, 若為空則回null
            descendingIterator() - 以相反的排列返回Deque的所有元素
        */
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution125 {
        public boolean isPalindrome(String s) {
            //透過左右指標
            int l = 0;
            int r = s.length() - 1;

            while(l < r){
                //找到數字或是英文字母
                while(l < r && !Character.isLetterOrDigit(s.charAt(l)))
                    l++;

                while(l < r && !Character.isLetterOrDigit(s.charAt(r)))
                    r--;

                //成對比較
                if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                    return false;

                l++;
                r--;
            }

            return true;
        }
    }

    //Time Complexity: O(n^2), Space Complexity: O(1), 每個字母都要左右展開s.length()長度
    class Solution5 {
        public String longestPalindrome(String s) {
            //直接進去左右展開
            int start = 0;
            int end = 0;
            for(int i=0; i<s.length(); i++){
                //!!有兩種展開方式, 奇數與偶數
                int[] odd = expand(s, i, i);//abcba
                int[] even = expand(s, i, i + 1);//abccba

                //更新數據
                if(odd[1] - odd[0] > end - start){
                    start = odd[0];
                    end = odd[1];
                }

                if(even[1] - even[0] > end - start){
                    start = even[0];
                    end = even[1];
                }
            }

            //假設都沒找到, 就會是回傳s.substring(0, 1)
            return s.substring(start, end + 1);
        }

        public int[] expand (String s, int l, int r){
            //記得排除邊界
            while(0 <= l && r < s.length() && s.charAt(l) == s.charAt(r)){
                l--;
                r++;
            }

            //奇數時, 至少會進入回圈一次, 所以要補回來, a => [-1 + 1, 1 - 1] = [0, 0]
            //偶數時, 會補回原本的位子, 若沒進入回圈則是會l + 1 > r - 1, 但不影響後續
            //, ab => [0 + 1, 1 - 1] = [1, 0], aa => [-1 + 1, 2 - 1] = [0, 1]
            return new int[]{l + 1, r - 1};
        }

        //Time Complexity: O(n^2), Space Complexity: O(n^2)
        public String longestPalindrome2(String s) {
            //透過2D - dp, dp[i][j]為i到j是否為對稱
            boolean[][] dp = new boolean[s.length()][s.length()];
            int len = 0;
            int start = 0;
            int end = 0;
            for(int j=1; j<s.length(); j++){
                for(int i=0; i<j; i++){
                    //有兩個條件, 兩邊要一樣, 並且內部也是要一樣(或是沒有內部, ex: a, aa)
                    if(s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])){
                        dp[i][j] = true;
                        if(j - i + 1 > len){
                            len = j - i + 1;//記得更新長度
                            start = i;
                            end = j;
                        }
                    }
                }
            }

            //假設都沒找到, 就會是回傳s.substring(0, 1)
            return s.substring(start, end + 1);
        }
    }

    //Time Complexity: O(n^2), Space Complexity: O(1), 每個字母都要左右展開s.length()長度
    class Solution647 {
        public int countSubstrings(String s) {
            //一樣使用左右展開
            int count = 0;
            for(int i=0; i<s.length(); i++){
                count += expand(s, i);
            }

            return count;
        }


        public int expand(String s, int mid){
            int count = 0;//這次展開有的回文數
            int exp = 0;
            //計算奇數 aaa
            while(0 <= mid - exp && mid + exp < s.length() && s.charAt(mid - exp) == s.charAt(mid + exp)){
                count++;//計算成功案例
                exp++;
            }

            exp = 0;//歸零
            //計算偶數 aa
            while(0 <= mid - exp && mid + exp + 1 < s.length() && s.charAt(mid - exp) == s.charAt(mid + exp + 1)){
                count++;//計算成功案例
                exp++;
            }

            return count;
        }

        /*
            aaa
            ^   res += "a", "aa"
            aaa
             ^  res += "a", "aaa", "aa"
            aaa
              ^ res += "a"
         */
    }

    //Time Complexity: O(n), Space Complexity: O(n), n = strs.size(), 解碼需要同等長度的記憶空間
    public class Solution271 {
        public char code = '$';

        public String encode(List<String> strs) {
            //本題要完成編碼與解碼兩個方法
            //設計編碼格式為[str長度][$][str]
            StringBuffer res = new StringBuffer();
            for(int i=0; i<strs.size(); i++){
                res.append(strs.get(i).length())
                        .append(code)
                        .append(strs.get(i));
            }
            return res.toString();
        }

        public List<String> decode(String str) {
            List<String> res = new ArrayList<String>();
            int head = 0;//用來判斷解碼的起始位子

            //從1開始, 至少會有"0$"
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == code) {
                    int len = Integer.parseInt(str.substring(head, i));//解碼開頭, 到自己的位子
                    res.add(str.substring(i + 1, i + 1 + len));//從'$'後開始, 所以要 + 1
                    head = i + 1 + len;//更新位置
                    i = head;
                }
            }

            return res;
            /* "abcde".substring(1, 3) = "bc"
                 ^ ^  =>包含頭不包含尾
                 1 3
            */
        }
    }

}