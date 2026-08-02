package leetCode.java;

//Slide Window O(n) O(Z), Z = 26
class Solution424 {
    public int characterReplacement(String s, int k) {
        // 可以替換k次, 相當於可以容許k個不相同, 最長長度可以是maxCnt + k
        int[] cnt = new int[26];
        int l = 0, r = 0, maxCnt = 0, res = 0;
        while (r < s.length()) {
            maxCnt = Math.max(maxCnt, ++cnt[s.charAt(r) - 'A']);

            if (r - l + 1 > maxCnt + k) { // 只需要if而不是while的原因為, l++之後必然為false
                --cnt[s.charAt(l) - 'A'];
                ++l;
            }

            res = Math.max(res, r - l + 1);
            ++r;
        }
        return res;
    }
}