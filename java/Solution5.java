package leetCode.java;

//Manacher's Algorithm O(n) O(n)
class Solution5 {
    public String longestPalindrome(String s) {
        // 插入#與頭尾
        StringBuilder sb = new StringBuilder();
        sb.append('^');
        for (char ch : s.toCharArray()) {
            sb.append('#').append(ch);
        }
        sb.append('#').append('$');
        String t = sb.toString();

        // 透過對稱性算出p[]
        int[] p = new int[t.length()];
        int c = 0, r = 0;
        int maxCenter = 0, maxLen = 0;
        for (int i = 1; i < t.length() - 1; ++i) { // 頭尾不判斷
            if (i < r) { // 在範圍內可以透過鏡像快速找到最小值
                p[i] = Math.min(r - i, p[2 * c - i]); // mirror position = c-(i-c)
            }

            // expand
            while(t.charAt(i - p[i] - 1) == t.charAt(i + p[i] + 1)) {
                ++p[i];
            }

            if (i + p[i] > r) { // 更新最靠右的邊界
                r = i + p[i];
                c = i;
            }

            if (p[i] > maxLen) {
                maxLen = p[i];
                maxCenter = i;
            }
        }

        // 還原資料
        return t.substring(maxCenter - maxLen, maxCenter + maxLen + 1)
            .replace("#", "");
    }

}
/*
Manacher's Algorithm 拉馬車算法

s = "bcbabcc"

1. 插入#與頭尾
t = "^#b#c#b#a#b#c#c#$"
此時不管從哪個字符看, 左右擴展長度=回文串長度
ex1: #b# -> b為中心左右擴展1, 回文長度1(b)
ex2: #b#c#b# -> c為中心左右擴展3, 回文長度3(bcb)
ex3: #c#c# -> #為中心左右擴展2, 回文長度2(cc)


2. 透過對稱性, 可以鏡像判斷回文長度
可以想成回文都是一朵朵香菇
P[i]表對於t[i]左右可以擴展多少
C表示蘑菇中心位置
R表示蘑菇右側邊界


                _________________________________________
        _________________________   |           _________
        _________   |               |           ____|____________
            |       |               |               |   |________
    _   _   |   _   |               |               |   |   |
    ^   #   b   #   c   #   b   #   a   #   b   #   c   #   c   #   $
P[] 0   0   1   0   3   0   1   0   5   0   1   0   1   0   1   0   0
                        ^---^           ^-------^   ^       ^
                        透過鏡像得到      透過鏡像得到  |
                                                    |
                                                    c的鏡像香菇超過a大菇的範圍,不能照抄
                                                    只能假設他最大可以碰到R
                                                    i距離R=R-i
                                                    距離中心i-C, 鏡像位置是 C-(i-C)=2*C-i
                                                    P[i]=min(R-i, P[2*C-i])


3. 求出所有P[]後, 最長回文字串=P[]中的最大值
*/


//Expand O(n^2) O(1)
class Solution5_2 {
    public String longestPalindrome(String s) {
        int[] res = new int[2]; // [位置, 長度]
        for (int i = 0; i < s.length(); ++i) {
            int[] odd = expand(i, i, s);
            int[] even = expand(i, i + 1, s);
            if (odd[1] > res[1]) {
                res = odd;
            }
            if (even[1] > res[1]) {
                res = even;
            }
        }

        return s.substring(res[0], res[0] + res[1]); // substring(l, r) 是範圍[l, r)
    }

    public int[] expand(int l, int r, String s) {
        while (0 <= l && r < s.length() && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        // len = (r-1) - (l+1) + 1
        return new int[]{l + 1, r - l - 1}; // 回傳{l, len}, 要還原範圍
    }
}