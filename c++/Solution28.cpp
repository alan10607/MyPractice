//Two Pointers O(mn) O(1), m = haystack.size(), n = needle.size()
class Solution28 {
public:
    int strStr(string haystack, string needle) {
        for (int i = 0;; ++i) {
            for (int j = 0;; ++j) {
                if (j == needle.size()) return i;
                if (i + j >= haystack.size()) return -1; // 走到底還沒有匹配
                if (haystack[i + j] != needle[j]) break; // 配對失敗, 從下一個頭開始
            }
        }
        return -1;
    }
};


//KMP O(m + n) O(1), m = s.size(), n = p.size()
class Solution28_2 {
public:
    int strStr(string s, string p) {
        vector<int> lps = buildLps(p);
        int j = 0;
        for (int i = 0; i < s.size(); ++i) {
            while (j > 0 && s[i] != p[j]) {
                j = lps[j - 1]; // 與buildLps一樣如果不匹配可以回到前一個相同前綴就好
            }

            if (s[i] == p[j]) {
                ++j;
            }

            if (j == p.size()) {
                return (i + 1) - p.size(); // i還在這個字母上, 先+1到下一個
            }
        }
        return -1;
    }

    vector<int> buildLps(string s) {
        vector<int> lps(s.length(), 0);
        int i = 1;
        int j = 0;
        for (int i = 1; i < s.length(); ++i) {
            while (j > 0 && s[i] != s[j]) {
                j = lps[j - 1]; // 退回之前有讀過的長度
            }

            if (s[i] == s[j]) {
                ++j; // 直接繼續找下一個
            }

            lps[i] = j;
        }
        return lps;
    }
};
/* KMP（Knuth-Morris-Pratt) 算法

如何建立 LPS(Longest Prefix Suffix) ?
這個表計算前綴與後綴相同的長度有多少
ex: abacabab

a,                  lps[0]=0, 必須跳過自己, 所以自己不能用, 為0
ab,       j=0, i=1, lps[1]=0
aba,      j=0, i=2, lps[2]=1, prefix=a
abac,     j=1, i=3, 不匹配, j退回lps[1]=0
abac,     j=0, i=3, 還是不匹配, lps[3]=0
abaca,    j=0, i=4, lps[4]=1, prefix=a
abacab,   j=1, i=5, lps[5]=2, prefix=ab
abacaba,  j=2, i=6, lps[6]=3, prefix=aba
abacabab, j=3, i=7, 不匹配, j退回lps[2]=1
abacabab, j=1, i=7, 匹配了, lps[7]=2, prefix=ab

lps={0,0,1,0,1,2,3,2}
*/