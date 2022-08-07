package leetCode.java;

//Greedy O(n) O(1)
class Solution678 {
    public boolean checkValidString(String s) {
        int maxLeft = 0;//判斷過程是否足夠匹配')'
        int minLeft = 0;//不會小於0, 用來判斷最後是否有剩餘的'('

        for(char ch : s.toCharArray()){
            if(ch == '('){
                maxLeft++;
                minLeft++;
            }else if(ch == ')'){
                maxLeft--;
                minLeft = Math.max(0, minLeft - 1);
                if(maxLeft < 0) return false;
            }else{
                maxLeft++;
                minLeft = Math.max(0, minLeft - 1);
            }
        }
        return minLeft == 0;
    }
}
/*
(**))
maxLeft	minLeft
1		1
2		0
3		-1->0
2		-1->0
1		-1->0
minLeft=0, true
*/