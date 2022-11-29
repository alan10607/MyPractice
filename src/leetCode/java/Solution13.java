package leetCode.java;

import java.util.Map;

//O(n) O(1)
class Solution13 {
    public int romanToInt(String s) {
        Map<Character, Integer> roman
                = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

        int res = 0;
        for(int i=0; i<s.length(); ++i){
            if(i > 0 && roman.get(s.charAt(i - 1)) < roman.get(s.charAt(i))){
                res += roman.get(s.charAt(i)) - 2 * (roman.get(s.charAt(i - 1)));
            }else{
                res += roman.get(s.charAt(i));
            }
        }
        return res;
    }
}