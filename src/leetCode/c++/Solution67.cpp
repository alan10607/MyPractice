//O(n) O(1)
class Solution67 {
public:
    string addBinary(string a, string b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        string res = "";
        while(i >= 0 || j >= 0){
            int num1 = i >= 0 ? a[i--] - '0' : 0;
            int num2 = j >= 0 ? b[j--] - '0' : 0;
            int sum = num1 + num2 + carry;//0 or 1 or 2 or 3
            res = to_string(sum % 2) + res;
            carry = sum / 2;
        }
        return carry == 1 ? "1" + res : res;
    }
};