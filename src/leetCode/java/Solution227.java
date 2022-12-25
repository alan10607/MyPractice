package leetCode.java;

//Stack O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution227 {
    public int calculate(String s) {
        int num = 0, calNum = 0, res = 0;
        char op = '+';
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                num = num * 10 + s.charAt(i) - '0';

            if(s.charAt(i) == '+' || s.charAt(i) == '-'
                    || s.charAt(i) == '*' || s.charAt(i) == '/' || i == s.length() - 1){
                if(op == '+') calNum += num;
                if(op == '-') calNum -= num;
                if(op == '*') calNum *= num;
                if(op == '/') calNum /= num;
                num = 0;
                op = s.charAt(i);

                if(s.charAt(i) == '+' || s.charAt(i) == '-' || i == s.length() - 1){
                    res += calNum;
                    calNum = 0;
                }
            }
        }
        return res;
    }
}