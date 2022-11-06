//O(m + n) O(Z), m = ransomNote.length(), n = magazine.length(), Z = 26
class Solution383 {
public:
    bool canConstruct(string ransomNote, string magazine) {
        unordered_map<char, int> counts;
        for(char c : magazine) ++counts[c];
        for(char c : ransomNote){
            if(--counts[c] < 0)
                return false;
        }
        return true;
    }
};