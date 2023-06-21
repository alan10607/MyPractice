package leetCode.java;

import java.util.*;

//Stack O(S + n) O(S + m), S = s.length(), n = 要重複的字串長度, m = 最大的[]層數
class Solution394 {
    public String decodeString(String s) {
        Deque<String> strDq = new LinkedList<>();
        Deque<Integer> numDq = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        for(char ch : s.toCharArray()){
            if(ch >= '0' && ch <= '9'){
                num = 10 * num + (ch - '0');
            }else if(ch == '['){
                strDq.push(sb.toString());
                sb.delete(0, sb.length());
                numDq.push(num);
                num = 0;
            }else if(ch == ']'){
                StringBuffer merge = new StringBuffer();
                merge.append(strDq.poll());
                int n = numDq.poll();
                for(int k=0; k<n; k++)
                    merge.append(sb);

                sb = merge;
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}