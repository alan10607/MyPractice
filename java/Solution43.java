package leetCode.java;

//O(mn) O(m + n)
class Solution43 {
    public String multiply(String num1, String num2) {
        //must not use any built-in BigInteger library or convert the inputs to integer directly
        int m = num1.length(), n = num2.length();
        int[] mul = new int[m + n]; // 位數最多剛好為兩數長度相加(m+n), ex: 99*99=9801
        for (int i = m - 1; i >= 0 ; --i) {
            for (int j = n - 1; j >= 0 ; --j) {
                mul[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                mul[i + j] += mul[i + j + 1] / 10; // 進位
                mul[i + j + 1] %= 10;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int num : mul) {
            if (res.isEmpty() && num == 0) { // 去掉開頭0
                continue;
            }
            res.append(Integer.toString(num));
        }
        return res.isEmpty() ? "0" : res.toString(); // 可能會有0*0
    }
}
/*
num1 = "123"
num2 = "456"
mul=int[6], 共六位數
mul index 開始= i + j + 1

index               0   1   2

                    1   2   3
        x           4   5   6
        ----------------------
                    7   3   8
                6   1   5
        +   4   9   2
        ----------------------
        0   5   6   0   8   8

index   0   1   2   3   4   5


*/