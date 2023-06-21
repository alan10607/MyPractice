//Greedy Slide Window O(n) O(Z), Z = 26
class Solution763 {
public:
    vector<int> partitionLabels(string s) {
        unordered_map<char, int> m;//<字母, 最後出現位置>
        for(int i=0; i<s.length(); ++i)
            m[s[i]] = i;

        vector<int> res;
        int l = 0, r = 0;
        for(int i=0; i<s.length(); ++i){
            r = max(r, m[s[i]]);
            if(i == r){//此時結束一個段落
                res.push_back(r - l + 1);
                l = r + 1;
            }
        }
        return res;
    }
};
/* 只需要關心該字母最後出現的位置即可

abcbaefgf
  ^^^^ ^^
*/