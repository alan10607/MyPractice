package leetCode.java;

//O(n) O(1)
class Solution8 {
    public int myAtoi(String s) {
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' ') i++;

        int sign = 1;
        if(i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')){
            sign = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }

        int res = 0;
        while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            int add = s.charAt(i++) - '0';
            add *= sign;

            if(res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && add >= Integer.MAX_VALUE % 10))
                return Integer.MAX_VALUE;

            if(res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE / 10 && add <= Integer.MIN_VALUE % 10))
                return Integer.MIN_VALUE;

            res = 10 * res + add;
        }
        return res;
    }
}