//Matrix O(mn) O(1)
class Solution73 {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        bool firstRowZero = false;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(matrix[i][j] == 0){//->
                    if(i == 0){
                        firstRowZero = true;
                    }else{
                        matrix[i][0] = 0;
                    }
                    matrix[0][j] = 0;// |
                }
            }
        }

        for(int i=1; i<m; ++i){//先計算內部, 否則外層會被影響
            for(int j=1; j<n; ++j){
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if(matrix[0][0] == 0){//先計算matrix[0][0], 否則0,0位置一樣會被影響
            for(int i=0; i<m; ++i)
                matrix[i][0] = 0;
        }

        if(firstRowZero){
            for(int j=0; j<n; ++j)
                matrix[0][j] = 0;
        }
    }
};
/*
    firstRowZero ->
    0   1   0   1
    |       |
    v       v
    1
    0 ->

*/