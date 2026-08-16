package leetCode.java;

import java.util.*;

//Backtracking O((4^n)/(n^(1/2))) O((4^n)/(n^(1/2))), Catalan number 卡塔蘭數, Cn = (2n)!/((n+1)!n!) = (4^n)/(n^(1/2))
class Solution22 {
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtracking("", 0, n);
        return res;
    }

    public void backtracking(String str, int balance, int n) {
        if (n == 0 && balance == 0) {
            res.add(str);
            return;
        }

        if (n < 0 || balance < 0) {
            return;
        }

        backtracking(str + "(", balance + 1, n - 1);
        backtracking(str + ")", balance - 1, n);
    }
}
/*
								(
				((									()
		(((				(()				()(
	   ((())) 		(()(   (())		()((	()()
				   (()())  (())()  ()(())  ()()()
*/