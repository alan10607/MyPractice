package leetCode.java;

//O(n) O(1
class Solution67 {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        String res = "";
        while(i >= 0 || j >= 0){
            int num1 = i >= 0 ? a.charAt(i--) - '0' : 0;
            int num2 = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = num1 + num2 + carry;
            res = (sum % 2) + res;
            carry = sum / 2;

        }
        return carry == 1 ? "1" + res : res;
    }
}