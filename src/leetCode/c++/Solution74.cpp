//Binary Search O(log(mn)) O(1)
class Solution74 {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int t = 0, b = matrix.size() - 1, l = 0, r = matrix[0].size() - 1, row = 0;
        while(t <= b){
            row = (t + b) / 2;
            if(target < matrix[row][l]){
                b = row - 1;
            }else if(matrix[row][r] < target){
                t = row + 1;
            }else{//matrix[row][l] <= target <= matrix[row][r]
                break;
            }
        }

        while(l <= r){
            int mid = (l + r) / 2;
            if(matrix[row][mid] == target){
                return true;
            }else if(target < matrix[row][mid]){
                r = mid - 1;
            }else{//matrix[row][mid] < target
                l = mid + 1;
            }
        }

        return false;
    }
};