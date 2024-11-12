//Matrix O(mn) O(1)
class Solution48 {
public:
    void rotate(vector<vector<int>>& matrix) {
        int l = 0, r = matrix.size() - 1;
        while(l < r){
            int t = l;
            int b = r;
            for (int i = 0; i + l < r; ++i) {
                int temp = matrix[t][l + i];
                matrix[t][l + i] = matrix[b - i][l];
                matrix[b - i][l] = matrix[b][r - i];
                matrix[b][r - i] = matrix[t + i][r];
                matrix[t + i][r] = temp;
            }
            ++l;
            --r;
        }
    }
};
/* 畫圖會更好理解
matrix[i][j], 則i代表從上到下, j代表從左到右
t/b是上下, l/r是左右, matrix[t/b, l/r]

[t,l]       [t,r]


[b,l]       [b,r]



各方向分別代表:

    ---> [t][l+i]
                    |
    ^   1   2   3   v
    |   4   5   6   [t+i][r]
[b-i][l]7   8   9
        <--- [b][r-i]


*/