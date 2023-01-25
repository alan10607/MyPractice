package leetCode.java;

//Binary Search O(n * log(r - l)) O(1), n = matrix.length, l = matrix[0][0], r = matrix[n - 1][n - 1]
class Solution378 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n - 1][n - 1];
        while(l < r){
            int mid = l + (r - l) / 2;//防止負數問題與更大容量
            if(k <= getRank(mid, matrix)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return r;
    }

    public int getRank(int target, int[][] matrix){
        int n = matrix.length;
        int cnt = 0, i = n - 1, j = 0;
        while(i >= 0 && j < n){
            if(target >= matrix[i][j]){
                cnt += i + 1;
                ++j;
            }else{
                --i;
            }
        }
        return cnt;
    }
}
/*
    vj
    1   2   3
    2   3   4
  i>3   4   5

 */