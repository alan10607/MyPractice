//O(n) O(1)
class Solution66 {
public:
    vector<int> plusOne(vector<int>& digits) {
        for(int i = digits.size() - 1; i>=0; --i){
            if(digits[i] == 9){
                digits[i] = 0;
            }else{
                digits[i] += 1;//不是原題+1就是進位
                return digits;
            }
        }

        digits.insert(digits.begin(), 1);//還沒return表示新進位

        return digits;
    }
};