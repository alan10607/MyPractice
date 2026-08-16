package leetCode.java;

//Fast & Slow Pointer O(logn) O(1)
class Solution202 {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        while (true) {
            slow = calc(slow);
            fast = calc(calc(fast));
            if (slow == fast) { // 判斷有無成環
                break;
            }
        }
        return slow == 1; // 判斷結果
    }

    public int calc(int num) {
        int sum = 0;
        while (num > 0) {
            int last = num % 10;
            sum += last * last;
            num /= 10;
        }
        return sum;
    }
}
/* 有可能會成環:
2 -> 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20
	 ^------------------------------------------|

*/