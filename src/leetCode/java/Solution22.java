package leetCode.java;

import java.util.*;

//Backtracking O((4^n)/(n^(1/2))) O((4^n)/(n^(1/2))), Catalan number 卡塔蘭數, Cn = (2n)!/((n+1)!n!) = (4^n)/(n^(1/2))
class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(n, n, "", res);
        return res;
    }

    public void backtracking(int start, int end, String str, List<String> res){
        if(start > end || start < 0 || end < 0) return;
        if(start == 0 && end == 0){
            res.add(str);
            return;
        }

        backtracking(start - 1, end, str + "(", res);
        backtracking(start, end - 1, str + ")", res);
    }
}
/*
								(
				((									()
		(((				(()				()(
	   ((())) 		(()(   (())		()((	()()
				   (()())  (())()  ()(())  ()()()
*/