//O(n) O(n)
class Solution217 {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_set<int> memo;
        for(int num : nums){
            if(memo.count(num)){
                return true;
            }else{
                memo.insert(num);
            }
        }
        return false;
    }
};