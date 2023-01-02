//LR Pointer O(n) O(1)
class Solution167 {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int l = 0, r = numbers.size() - 1;
        while(l < r){
            if(l > 0 && numbers[l] == numbers[l - 1]){
                ++l;
                continue;
            }
            if(r < numbers.size() - 1 && numbers[r] == numbers[r + 1]){
                --r;
                continue;
            }

            int sum = numbers[l] + numbers[r];
            if(sum == target) return {l + 1, r + 1};//題目的index從1開始
            if(sum < target){
                ++l;
            }else{
                --r;
            }
        }
        return {};
    }
};