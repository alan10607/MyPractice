//Fast & Slow Pointer O(logn) O(1)
class Solution202 {
public:
    bool isHappy(int n) {
        int fast = n, slow = n;
        while(true){
            fast = calc(calc(fast));
            slow = calc(slow);
            if(fast == slow) break;
        }
        return slow == 1;//判斷是否都為1
    }

    int calc(int num){
        int res = 0;
        while(num > 0){
            int last = num % 10;
            res += last * last;
            num /= 10;
        }
        return res;
    }
};
/*
2 4 16 37 58 89 145 42 20 4...

*/