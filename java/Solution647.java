package leetCode.java;

//Expand O(n^2) O(1)
class Solution647 {
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            res += expand(i, i, s);
            res += expand(i, i + 1, s);
        }
        return res;
    }

    public int expand(int l, int r, String s) {
        int cnt = 0;
        while (0 <= l && r < s.length() && s.charAt(l) == s.charAt(r)) {
            ++cnt;
            --l;
            ++r;
        }
        return cnt;
    }
}


//Manacher's Algorithm O(n) O(n)
class Solution647_2 {
    public int countSubstrings(String s) {
        // 也可以透過拉馬車算法, 詳見Solution 5
        StringBuilder sb = new StringBuilder();
        sb.append('^');
        for (char ch : s.toCharArray()) {
            sb.append('#').append(ch);
        }
        sb.append('#').append('$');
        String t = sb.toString();

        int[] p = new int[t.length()];
        int c = 0, r = 0, res = 0;
        for (int i = 1; i < t.length() - 1; ++i) {
            if (i < r) {
                p[i] = Math.min(r - i, p[2 * c - i]);
            }
            while (t.charAt(i - p[i] - 1) == t.charAt(i + p[i] + 1)) {
                ++p[i];
            }

            if (i + p[i] > r) {
                r = i + p[i];
                c = i;
            }

            res += (p[i] + 1) / 2;          
        }
        return res;
    }
}
/*
ex: s = "aaa"
t= "^#a#a#a#$"

t[i]=   ^   #   a   #   a   #   a   #   $
i=      0   1   2   3   4   5   6   7   8
p[i]=   0   0   1   2   3   2   1   0   0

p[i]=1, 可能回文有:a, 共1種
p[i]=2, 可能回文有:aa, 共1種
p[i]=3, 可能回文有:aba, b, 共2種
p[i]=4, 可能回文有:abba, bb, 共2種
p[i]=5, 可能回文有:abcba, bcb, c, 共3種

p[i]=n, 可能有(n+1)/2種

*/