//Backtracking O(n * n!) O(n)
class Solution47 {
public:
    vector<vector<int>> res;

    vector<vector<int>> permuteUnique(vector<int>& nums) {
        vector<int> vec;
        vector<bool> visited(nums.size());
        sort(nums.begin(), nums.end());//透過排序偵測上一個是否一樣
        backtracking(vec, visited, nums);
        return res;
    }

    void backtracking(vector<int>& vec, vector<bool>& visited, vector<int>& nums) {
        if(vec.size() == nums.size()){
            res.push_back(vec);
            return;
        }

        for(int i=0; i<nums.size(); ++i){
            if(visited[i]) continue;
            if(i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;//重覆時必須依位置入列, 前一個有入列才可入列

            visited[i] = true;
            vec.push_back(nums[i]);
            backtracking(vec, visited, nums);
            vec.pop_back();
            visited[i] = false;
        }
    }
};
/*
if(i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
重複的情況下, 若前一個未被拜訪就跳過
有拜訪則可入列, 如此重複時便會照index順序入列, 依照index0, 1, 2...依序加入
nums = [1,1,2]

1   1   2
(0) (1) (2)

1   1   2
(1) (0) (2) 不合法, continue

1   2   1
(0) (2) (1)

1   2   1
(1) (2) (0) 不合法, continue

2   1   1
(2) (0) (1)

2   1   1
(2) (1) (0) 不合法, continue

*/