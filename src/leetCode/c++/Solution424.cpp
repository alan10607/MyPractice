//Slide Window O(n) O(Z), Z = 26
class Solution424 {
public:
    int characterReplacement(string s, int k) {
        int l = 0, r = 0, maxCnt = 0, res = 0;
        vector<int> cnt(26, 0);
        while(r < s.length()){
            maxCnt = max(maxCnt, ++cnt[s[r] - 'A']);

            if(r - l + 1 > maxCnt + k)
                --cnt[s[l++] - 'A'];//只需要if而不是while的原因為, l++之後必然為false

            res = max(res, r - l + 1);
            ++r;
        }
        return res;
    }
};