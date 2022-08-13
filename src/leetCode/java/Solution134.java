package leetCode.java;

//Greedy O(n) O(1)
class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //1 所有gas > cost, 則存在解
        int all = 0;
        for(int i=0; i<gas.length; i++)
            all += (gas[i] - cost[i]);

        if(all < 0) return -1;

        //2 若透支則跳下一個
        int res = 0;
        int tank = 0;
        for(int i=0; i<gas.length; i++){
            tank += (gas[i] - cost[i]);
            if(tank < 0){
                res = i + 1;
                tank = 0;
            }
        }
        return res;
    }
}
/* 已知sum(gas)>sum(cost), 走到底若有餘數, 則代表走回頭也會>=0
gas =  [ 1, 2, 3, 4, 5]
cost = [ 3, 4, 5, 1, 2]
        -2 -2 -2  3  3
				  ^
*/