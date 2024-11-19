//O(k + n) O(1), k = updates.size(), n = length, 空間除了題目所需沒有其他故O(1)
class Solution370 {
public:
    vector<int> getModifiedArray(int length, const vector<vector<int>>& updates) {
        vector<int> diff(length + 1, 0); // 把diff當作nums做就可以空間O(1), 需要n+1來避免r+1出界
        for(vector<int> update : updates) {
            int l = update[0];
            int r = update[1];
            int val = update[2];
            diff[l] += val;
            diff[r + 1] -= val; 
            
        }
        
        for(int i = 1; i < length; ++i) {
            diff[i] += diff[i - 1];
        }
        diff.resize(length); // 去掉最後的
        return diff;
    }
};
/*
可以透過可以透過差分法獲得差異

        0     1     2     3     4     5
nums=   8 <-- 2 <-- 6 <-- 3 <-- 1 
            \     \     \
diff=   8     -6    4     -3    -2    -1
    
如果要[1,3]都+3, 則diff[1]+3, diff[4]-3
diff=   8     -3    4     -3    -5    -1

重新計算, 可以得到新的nums
nums=   8     5     9     6     1
    
*/