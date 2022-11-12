//DP O(n) O(1)
class Solution70 {
public:
    int climbStairs(int n) {
        int one = 1;
        int two = 0;
        for(int i=1; i<=n; ++i){
            int temp = one;
            one = one + two;
            two = temp;
        }
        return one;
    }
};