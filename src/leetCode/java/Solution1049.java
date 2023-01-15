package leetCode.java;

//DP KP O(mn) O(n), m = stones.length, n = target = sum / 2
class Solution1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int stone : stones) sum += stone;

        int target = sum / 2;
        boolean dp[] = new boolean[target + 1];//dp[i]表是否可以從stones中組合出i
        dp[0] = true;
        for(int stone : stones){
            for(int i=target; i>=stone; --i){//0-1背包問題
                dp[i] |= dp[i - stone];
            }
        }

        for(int i=target; i>=0; --i){
            if(dp[i]) return sum - 2 * i;
        }
        return 0;
    }
}