package leetCode.java;

//Greedy O(n) O(1)
class Solution45 {
    public int jump(int[] nums) {
        int rightMost = 0;
        int move = 0;//BFS每層的最大步數
        int res = 0;

        //走到nums.length - 1就可以break
        for(int i=0; i < nums.length - 1; i++){
            move = Math.max(move, i + nums[i]);
            if(i == rightMost){
                res++;
                rightMost = move;//更新下一次BFS範圍
                if(rightMost >= nums.length - 1)
                    return res;
            }
        }
        return res;
    }
}
/*  BFS會比DP快

        0 1 2 3 4
nums = [2,3,0,1,4]
				  n[0]
		 n[1]              n[2]
	n[2] n[3] n[4]          x
	 x	 n[4]

*/