//Slide Window O(nL) O(nL), n = s.length, L=要匹配的長度本題=10
class Solution187 {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        vector<string> res;
        unordered_map<string, int> cnt;
        for (int i = 9; i < s.length(); ++i) {
            string sub = s.substr(i - 9, 10);
            if (cnt[sub] == 1) {
                res.push_back(sub);
            }
            ++cnt[sub];
        }
        return res;
    }
};