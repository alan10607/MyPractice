//O(mn) O(1), m = strs.size(), n = strs[0].length()
class Solution14 {
public:
    string longestCommonPrefix(vector<string>& strs) {
        for (int i = 0; i < strs[0].length(); ++i) {
            char ch = strs[0][i];
            for (int j = 1; j < strs.size(); ++j) {
                if (i >= strs[j].length() || ch != strs[j][i]) {
                    return strs[0].substr(0, i);
                }
            }
        }

        return strs[0]; // 代表全都一樣
    }
};