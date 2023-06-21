package leetCode.java;

//Fast & Slow Pointer O(logn) O(1)
class Solution202 {
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        while(true){
            fast = calc(calc(fast));
            slow = calc(slow);
            if(fast == 1) break;
            if(slow == fast) return false;
        }
        return true;
    }

    public int calc(int n){
        int sum = 0;
        while(n != 0){
            int last = n % 10;
            sum += last * last;
            n /= 10;
        }
        return sum;
    }
}
/*
2 -> 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20
	 ^------------------------------------------|

*/