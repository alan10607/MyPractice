//Backtracking O(2^n * n) O(n), n = word.length()
class Solution320 {//lintcode779
public:
    vector<string> generateAbbreviations(string &word) {
        int n = word.length();
        vector<string> res;
        for(int i = 0; i < (1 << n); ++i){//pow(2, n)
            string str = "";
            int cnt = 0;
            for(int j=0; j<n; ++j){
                if(((i >> j) & 1) == 1){//從左邊開始配對就可以, 因為不論怎樣都包含所有可能了
                    ++cnt;
                }else{
                    if(cnt > 0){
                        str += to_string(cnt);
                        cnt = 0;
                    }
                    str += word[j];
                }
            }
            if(cnt > 0) str += to_string(cnt);//記得加入最後

            res.push_back(str);
        }
        return res;
    }
};
/* word = "abc"

000 abc
001 ab1
010 a1c
011 a2
100 1bc
101 1b1
110 2c
111 3

*/