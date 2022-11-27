//Matrix O(mn) O(1), 空間複雜度不含答案所需
class Solution54 {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int t = 0, b = matrix.size() - 1, l = 0, r = matrix[0].size() - 1;
        vector<int> res;
        while(true){
            for(int i=l; i<=r; ++i)
                res.push_back(matrix[t][i]);

            if(++t > b) break;

            for(int i=t; i<=b; ++i)
               res.push_back(matrix[i][r]);

            if(l > --r) break;

            for(int i=r; i>=l; --i)
               res.push_back(matrix[b][i]);

            if(t > --b) break;

            for(int i=b; i>=t; --i)
               res.push_back(matrix[i][l]);

            if(++l > r) break;
        }
        return res;
    }
};