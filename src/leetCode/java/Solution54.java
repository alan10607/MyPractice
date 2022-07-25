package leetCode.java;

import java.util.*;

//Matrix O(mn) O()
class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int t = 0;
        int b = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;
        while(true){
            for(int i=l; i<=r; i++)
                res.add(matrix[t][i]);

            if(++t > b) break;

            for(int i=t; i<=b; i++)
                res.add(matrix[i][r]);

            if(l > --r) break;

            for(int i=r; i>=l; i--)
                res.add(matrix[b][i]);

            if(t > --b) break;

            for(int i=b; i>=t; i--)
                res.add(matrix[i][l]);

            if(++l > r) break;
        }
        return res;
    }
}
/*
	vl			vr
  t>1	2	3	4
	5	6	7	8
  b>9	10	11	12
*/