package leetCode.java;

//Binary Search O(log(mn)) O(1)
class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 兩種解法: 把m*n轉為一維陣列, 或是直接做兩次binary search
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int num = matrix[mid / n][mid % n];
            if (num == target) {
                return true;
            } else if (num < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
/*
ex:

    n ->
m   0   1   2   3
|   4   5   6   7
v   8   9   10  11

row = mid / n
col = mid % n
num = matrix[mid / n][mid % n]

*/


//Binary Search O(log(mn)) O(1)
class Solution74_2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int t = 0;
        int b = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;

        int row = 0;
        while(t <= b){
            row = (t + b) / 2;
            if(target < matrix[row][l]){
                b = row - 1;
            }else if(matrix[row][r] < target){
                t = row + 1;
            }else{//matrix[mid][l] <= target <= matrix[mid][r]
                break;
            }
        }

        int col = 0;
        while(l <= r){
            col = (l + r) / 2;
            if(target < matrix[row][col]){
                r = col - 1;
            }else if(matrix[row][col] < target){
                l = col + 1;
            }else{//target == matrix[row][col]
                return true;
            }
        }
        return false;
    }
}
/*
先比較一行的左與右側
找到區間之後再次二分

1	2	3	4
[5]	6	7	[8]
9	10	11	12
*/