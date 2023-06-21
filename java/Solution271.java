package leetCode.java;

import java.util.*;

//encode(): O(n) O(n), decode(): O(n) O(1)
class Solution271 {//lintcode659
    public String encode(List<String> strs) {
        StringBuffer res = new StringBuffer();
        for(int i=0; i<strs.size(); i++){
            res.append(strs.get(i).length())
                    .append("$")
                    .append(strs.get(i));
        }
        return res.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int start = 0;
        //從1開始, 至少會有"0$"
        for(int i=1; i<str.length(); i++){
            if(str.charAt(i) == '$'){
                int len = Integer.parseInt(str.substring(start, i));
                res.add(str.substring(i + 1, i + 1 + len));
                start = i + len + 1;
                i = start;//不用再+1, 留給for迴圈i++
            }
        }
        return res;
    }
}

