package leetCode.java;

//Matrix O(mn) O(1)
class Solution73 {
    public void setZeroes(int[][] matrix) {
        //uses O(1) space
        int m = matrix.length;
        int n = matrix[0].length;
        boolean isFirstRowZero = false;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 0){
                    //處理 - 方向
                    if(i == 0){
                        isFirstRowZero = true;
                    }else{
                        matrix[i][0] = 0;
                    }

                    //處理 | 方向
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if(matrix[0][0] == 0){
            for(int i=0; i<m; i++)
                matrix[i][0] = 0;
        }

        if(isFirstRowZero){
            for(int j=0; j<n; j++)
                matrix[0][j] = 0;
        }
    }
}
/*

    [0,0]  isZero <-------
       ^
       |
       |
       |

*/