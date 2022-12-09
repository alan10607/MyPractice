//Matrix O(mn) O(1)
class Solution48 {
public:
    void rotate(vector<vector<int>>& matrix) {
        int l = 0, r = matrix.size() - 1;
        while(l < r){
            int t = l;
            int b = r;
            for(int i=0; l + i < r; ++i){
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
/*

[t,l]       [t,r]


[b,l]       [b,r]

*/