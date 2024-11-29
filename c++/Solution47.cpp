//Backtracking O(n * n!) O(n)
class Solution47 {
public:
    vector<vector<int>> res;

    vector<vector<int>> permuteUnique(vector<int>& nums) {
        sort(nums.begin(), nums.end()); // 透過排序偵測上一個是否一樣

        vector<int> selected;
        vector<bool> visited(nums.size());
        backtracking(visited, selected, nums);
        return res;
    }

    void backtracking(vector<bool>& visited, vector<int>& selected, vector<int>& nums) {
        if (selected.size() == nums.size()) {
            res.push_back(selected);
            return;
        }

        for (int i = 0; i < nums.size(); ++i) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue; // 重覆時必須依位置入列, 前一個有入列才可入列

            visited[i] = true;
            selected.push_back(nums[i]);
            backtracking(visited, selected, nums);
            visited[i] = false;
            selected.pop_back();
        }
    }
};

/*
為什麼要判斷 if(.... && !visited[i - 1]) continue; ?

nums=[2,2,2], 可以有以下組合
[2,2',2''] [2,2'',2']
[2',2,2''] [2',2'',2]
[2'',2,2'] [2'',2',2]

只希望留下順階的[2,2',2''], 所以前一個沒有visted就要跳過
ex: selected=[2]遇到2''時, 前一個2'沒有被使用, 要跳過,
selected就不會有[2,2''...]

反之有visted就不跳過
ex: selected=[2]遇到2'時, selected會繼續[2,2'...]


*/