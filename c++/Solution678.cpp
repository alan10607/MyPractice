//Greedy O(n) O(1)
class Solution678 {
public:
    bool checkValidString(string s) {
        int maxCnt = 0, minCnt = 0;
        for(char ch : s){
            if(ch == '('){
                ++maxCnt;
                ++minCnt;
            }else if(ch == ')'){
                if(--maxCnt < 0) return false;
                minCnt = max(0, minCnt - 1);
            }else{//ch == '*'
                ++maxCnt;
                minCnt = max(0, minCnt - 1);
            }
        }
        return minCnt == 0;
    }
};
/*
    (   *   *   )   )
max 1   2   3   2   1   判斷(是否過少
min 1   0   0   0   0   判斷(是否過多

*/