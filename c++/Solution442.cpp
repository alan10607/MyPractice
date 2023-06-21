//O(n) O(1)
class Solution442 {
public:
    vector<int> findDuplicates(vector<int>& nums) {
        for(int i=0; i<nums.size(); ++i){
            while(nums[i] != nums[nums[i] - 1]){
                swap(nums[i], nums[nums[i] - 1]);
            }
        }

        vector<int> res;
        for(int i=0; i<nums.size(); ++i)
            if(i != nums[i] - 1) res.push_back(nums[i]);

        return res;
    }
};
/*
2   1   1   3   2
^

1   2   1   3   2
            ^

1   2   3   1   2
                    ^

res=1,2
*/