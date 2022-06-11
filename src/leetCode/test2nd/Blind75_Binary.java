package leetCode.test2nd;

public class Blind75_Binary {

    //Binary
    class Solution371 {
        public int getSum(int a, int b) {
            while(b != 0){
                int temp = a;
                a = a ^ b;
                b = (temp & b) << 1;
            }
            return a;
        }
    }

    //Binary
    class Solution191 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0;
            for(int i=0; i<32; i++){
                //==優先權高於&, 要先括號&, 否則是n & (1 == 1)
                if((n & 1) == 1) count++;
                n = n >>> 1;
            }
            return count;
        }
    }

    //*Binary + DP
    class Solution338 {
        public int[] countBits(int n) {
            int highBit = 0;//最高bit的值
            int[] dp = new int[n + 1];
            dp[0] = 0;
            for(int i=1; i<=n; i++){
                //關鍵在取出最高位數的值, 4 => 100 * 011 剛好 = 0
                if((i & (i - 1)) == 0)
                    highBit = i;

                dp[i] = 1 + dp[i - highBit];
            }
            return dp;
        }
    }

    //Binary
    class Solution268 {
        public int missingNumber(int[] nums) {
            //用HashMap要額外空間, 更好的方法是Binary
            int xor = 0;
            for(int i=0; i<=nums.length; i++)
                xor ^= i;

            for(int num : nums)
                xor ^= num;

            return xor;
        }
    }

    //*Binary
    class Solution190 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int reverse = 0;
            for(int i=0; i<32; i++){
                reverse |= (n & 1) << (31 - i);//取出右位
                n >>>= 1;
            }
            return reverse;
        }
    }

}