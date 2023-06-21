package leetCode.java;

import java.util.*;

//Backtracking O(n * n!) O(n), 最差的情況下每個字母可以都是回文, 即需要n!次遞迴
class Solution131 {
    public List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        //題目要求以內層List包裝成s的拆分, 用DFS
        backtracking(0, new ArrayList<String>(), s);
        return res;

    }

    public void backtracking(int i, List<String> list, String s){
        if(i == s.length()){
            res.add(new ArrayList<String>(list));//O(n)
            return;
        }

        for(int j = i + 1; j<=s.length(); j++){
            String str = s.substring(i, j);
            if(isPali(str)){
                list.add(str);
                backtracking(j, list, s);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPali(String str){
        int l = 0;
        int r = str.length() - 1;
        while(l < r){
            if(str.charAt(l) != str.charAt(r))
                return false;

            l++;
            r--;
        }
        return true;
    }
}
/* s = "aab"
				[]
		a		aa		aab
     a    ab    b
	 b
*/