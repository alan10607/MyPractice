package leetCode.java;

import java.util.*;

//Interval O(n) O(1), n = seats.length
class Solution849 {
    public int maxDistToClosest(int[] seats) {
        int res = 0 ; // 回傳距離
        int last = -1; // 上次位置
        int n = seats.length;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 0) {
                continue;
            }

            if (last == -1) { // 左側沒人, 靠牆坐在0
                res = Math.max(res, i);
            } else {
                res = Math.max(res, (i - last) / 2);
            }
            last = i;
        }

        res = Math.max(res, n - 1 - last); // 右側沒人, 靠牆坐在n-1
        return res;
    }
}
/*
靠牆情況:
(0,4]會坐在0, 距離=4
        0   1   2   3   4   5
seats=  *               1


[4,5)會坐在5, 距離=1
        0   1   2   3   4   5
seats=                  1   *


偶數情況:
[0,4]會坐在2, 最近距離=2
        0   1   2   3   4   5
seats=  1       *       1


奇數情況:
[0,5]會坐在2, 最近距離=2
        0   1   2   3   4   5
seats=  1       *           1

*/