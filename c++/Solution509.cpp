//DP O(n) O(1)
class Solution509 {
public:
    int fib(int n) {
        if (n < 2) return n;
        
        int first = 1, second = 0;
        for (int i = 2; i <= n; ++i) {
            int tmp = first;
            first = first + second;
            second = tmp;
        }
        return first;
    }
};