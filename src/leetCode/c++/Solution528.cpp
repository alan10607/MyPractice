//528 Binary Search Solution(): O(n) O(n), pickIndex(): O(logn) O(n)
class Solution {
public:
    vector<int> nums;//累計數字

    Solution(vector<int> w) {
        for(int num : w)
            nums.push_back(nums.empty() ? num : nums.back() + num);
    }

    int pickIndex() {//回傳的是位置
        int r = rand() % nums.back();//0<=r<最大, ubber_bound不會超過最大
        return upper_bound(nums.begin(), nums.end(), r) - nums.begin();//減去begin()是為了取得位置
    }
};