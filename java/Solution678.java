package leetCode.java;

//Greedy O(n) O(1)
class Solution678 {
    public boolean checkValidString(String s) {
        int maxBalance = 0; // 嘗試留下最多的'(', 用來判斷數量是否足夠匹配')'
        int minBalance = 0; // 嘗試留下最少的'(', 此值不會小於0, 用來判斷最後是否有剩餘的'('
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                ++maxBalance;
                ++minBalance;
            } else if (ch == ')') {
                --maxBalance;
                minBalance = Math.max(0, minBalance - 1);
                if (maxBalance < 0) { // ')'太多
                    return false;
                }
            } else { // ch == '*'
                ++maxBalance;
                minBalance = Math.max(0, minBalance - 1); 
            }
        }

        return minBalance == 0; //minBalance>0, 則表示'(‘有多餘沒被配對到的
    }
}
/*
    (   *   *   )   )
max 1   2   3   2   1   判斷(是否過少
min 1   0   0   0   0   判斷(是否過多

*/
*/