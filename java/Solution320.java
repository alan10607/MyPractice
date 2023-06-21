package leetCode.java;

import java.util.*;

//Backtracking O(2^n * n) O(n), n = word.length()
class Solution320 {//lintcode779
    public List<String> generateAbbreviations(String word) {
        int n = word.length();
        List<String> res = new ArrayList<>();
        for(int i = 0; i < (1 << n); ++i){//Math.pow(2, n)
            String str = "";
            int cnt = 0;
            for(int j=0; j<n; ++j){
                if(((i >> j) & 1) == 1){
                    ++cnt;
                }else{
                    if(cnt > 0){
                        str += Integer.toString(cnt);
                        cnt = 0;
                    }
                    str += word.charAt(j);
                }
            }
            if(cnt > 0) str += Integer.toString(cnt);

            res.add(str);
        }
        return res;
    }
}