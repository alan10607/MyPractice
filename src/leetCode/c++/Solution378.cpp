//Binary Search O(n * log(r - l)) O(1), n = matrix.size(), l = matrix[0][0], r = matrix[n - 1][n - 1]
class Solution378 {
public:
    int kthSmallest(vector<vector<int>>& matrix, int k) {
        int l = matrix[0][0], r = matrix.back().back();
        while(l < r){
            int mid = l + (r - l) / 2;//防止負數, ex: (-5+(-4))/2=-4, -5+(-4-(-5))/2=-5
            if(k <= getRank(mid, matrix)){//可以再縮小
                r = mid;//保留大於的值
            }else{
                l = mid + 1;
            }
        }
        return r;
    }

    int getRank(int target, vector<vector<int>>& matrix){
        int i = matrix.size() - 1, j = 0, cnt = 0;
        while(i >= 0 && j <= matrix.size() - 1){
            if(matrix[i][j] <= target){
                cnt += i + 1;//加入一整列
                ++j;
            }else{
                --i;
            }
        }
        return cnt;
    }
};
/* rows and columns is sorted in ascending, 行列都是排序過

mid=7

1   2   3 __4|
2   4   6|  8
3   6 __7|  9
4   7|  10  12

*/