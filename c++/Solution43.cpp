//O(mn) O(m + n)
class Solution43 {
public:
    string multiply(string num1, string num2) {
        int m = num1.length(), n = num2.length(), carry = 0;
        vector<int> mult(m + n);//99*99=9081
        for(int i = m - 1; i >= 0; --i){
            for(int j = n - 1; j >= 0; --j){
                mult[i + j + 1] += (num1[i] - '0') * (num2[j] - '0');
                mult[i + j] += mult[i + j + 1] / 10;
                mult[i + j + 1] = mult[i + j + 1] % 10;
            }
        }

        string res = "";
        for(int num : mult)
            if(!res.empty() || num != 0) res.push_back(num + '0');

        return res.empty() ? "0" : res;
    }
};