package leetCode.java;

//Backtracking O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution224 {
    public int calculate(String s) {
        int res = 0, num = 0, op = 1;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                int start = i, cnt = 0;
                for(; i<s.length(); i++) {
                    if(s.charAt(i) == '(') cnt++;
                    if(s.charAt(i) == ')') cnt--;
                    if(cnt == 0) break;
                }
                num = calculate(s.substring(start + 1, i));
            }

            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                num = num * 10 + (s.charAt(i) - '0');

            if(s.charAt(i) == '+' || s.charAt(i) == '-' || i == s.length() - 1){
                res += op * num;
                num = 0;
                op = s.charAt(i) == '+' ? 1 : -1;
            }
        }
        return res;
    }
}