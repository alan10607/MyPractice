//O(mn) O(1), m = strs.size(), n = strs[0].length()
class Solution14 {
public:
    string longestCommonPrefix(vector<string>& strs) {
        for(int i=0; i<strs[0].length(); ++i){
            char ch = strs[0][i];
            for(string str : strs){
                if(i >= str.length() || ch != str[i])
                    return str.substr(0, i);
            }
        }
        return strs[0];//代表全都一樣
    }
};