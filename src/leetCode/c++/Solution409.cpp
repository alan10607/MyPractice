//O(n) O(Z), Z=26
class Solution409 {
public:
    int longestPalindrome(string s) {
        unordered_set<char> memo;
        int res = 0;
        for(char ch : s){
            if(memo.count(ch)){
                memo.erase(ch);
                res += 2;
            }else{
                memo.insert(ch);
            }
        }
        return memo.empty() ? res : res + 1;
    }
};