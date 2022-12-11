//O(n * logn * logZ) O(logn), n = nums.length, Z = num轉為字串的長度(最大32), 空間複雜度為排序所需
class Solution179 {
public:
    string largestNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end(), [](int a, int b){//字串大排到小
            return to_string(a) + to_string(b) > to_string(b) + to_string(a);
        });
        string res = "";
        for(int num : nums)
            res += to_string(num);
        
        return res[0] == '0' ? "0" : res;//避免開頭為0的情況, ex:"00"
    }
};
/*nums=3,34,30

334 < 343 => 34 > 3
3430 > 3034 => 34 > 30
330 > 303 => 3 > 30

34 > 3 > 30
res=34330

*/