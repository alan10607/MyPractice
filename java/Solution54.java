package leetCode.java;

import java.util.*;

//Matrix O(mn) O(1), 空間複雜度不含答案所需
class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int t = 0, b = matrix.length - 1;
        int l = 0, r = matrix[0].length - 1;
        List<Integer> res = new ArrayList<>();
        while (true) {
            for (int i = 0; l + i <= r; ++i) {
                res.add(matrix[t][l + i]);
            }
            if (++t > b) {
                break;
            }
            

            for (int i = 0; t + i <= b; ++i) {
                res.add(matrix[t + i][r]);
            }
            if (l > --r) {
                break;
            }

            for (int i = 0; r - i >= l; ++i) {
                res.add(matrix[b][r - i]);
            }
            if (t > --b) {
                break;
            }

            for (int i = 0; b - i >= t; ++i) {
                res.add(matrix[b - i][l]);
            }
            if (++l > r) {
                break;
            }
        }
        return res;
    }
}
/*
                    n=matrix[0].length

                    l    		r
                t   1	2	3	4
m=matrix.length     5	6	7	8
                b   9	10	11	12
*/