package leetCode.java;

//Greedy O(n) O(1)
class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // If there exists a solution, it is guaranteed to be unique -> 題目保證唯一解
        int sum = 0;
        for (int i = 0; i < gas.length; ++i) {
            sum += (gas[i] - cost[i]);
        }
        if (sum < 0) { // sum >= 0 表存在解
            return -1;
        }

        int remain = 0, res = 0;
        for (int i = 0; i < gas.length; ++i) {
            remain += (gas[i] - cost[i]);
            if (remain < 0) {
                res = i + 1; // 嘗試下一個
                remain = 0; // 捨棄這次當起點, 結餘歸0
            }
        }
        return res;
    }
}
/* 
已知sum(gas - cost) = total >= 0
若找到答案 res, 從 res 走到 n-1 時, remain >= 0

又因為整體總和 >= 0
[res, n-1]的remain + [0, res-1]的remain = total >= 0
因此走回頭時, 剩餘油量足以補足 [0, res-1], 可以完整走回 res


gas =  [ 1, 2, 3, 4, 5]
cost = [ 3, 4, 5, 1, 2]
        -2 -2 -2  3  3
				  ^
*/