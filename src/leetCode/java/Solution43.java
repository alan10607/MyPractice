package leetCode.java;

//O(mn) O(m + n)
class Solution43 {
    public String multiply(String num1, String num2) {
        //must not use any built-in BigInteger library or convert the inputs to integer directly
        if("0".equals(num1) || "0".equals(num2)) return "0";

        int m = num1.length();
        int n = num2.length();
        int[] resArr = new int[m + n];//最多剛好為兩數字位數相加
        for(int i = m - 1; i>=0; i--){
            for(int j = n - 1; j>=0; j--){
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                int mult = a * b + resArr[i + j + 1];//要記得加上之前的數
                resArr[i + j] += mult / 10;
                resArr[i + j + 1] = mult % 10;
            }
        }

        StringBuffer res = new StringBuffer();
        boolean startFlag = false;
        for(int num : resArr){
            if(!startFlag && num != 0)
                startFlag = true;

            if(startFlag)
                res.append(num);//底層會int轉char
        }

        return res.toString();
    }
}
/*
			9	9
x			9	9
------------------
		8	9	1
	8	9	1
-------------------
	9	8	0	1

*/