package leetCode.java;

//O(n) O(1)
class Solution66 {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i>=0; i--){
            if(digits[i] == 9){
                digits[i] = 0;//剛好進位
            }else{
                digits[i] += 1;
                return digits;
            }
        }

        int[] res = new int[digits.length + 1];
        res[0] = 1;
        for(int i=0; i<digits.length; i++)
            res[i + 1] = digits[i];

        return res;
    }
}