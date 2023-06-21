//O(nk) O(nZ), n = strs.size(), k為最大的str長度, Z = 26
class Solution49 {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> memo;

        for(string str : strs){
            string hash(26, 0);
            for(char ch : str)
                ++hash[ch - 'a'];

            memo[hash].push_back(str);
        }

        vector<vector<string>> res;
        for(auto it : memo)
            res.push_back(it.second);

        return res;
    }
};