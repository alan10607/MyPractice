package leetCode.java;

//Greedy O(n) O(1)
class Solution45 {
    public int jump(int[] nums) {
        int rightMost = 0; // 目前最遠可以到哪
        int jumpEnd = 0; // 這一跳的右邊界
        int res = 0;
        //只需要到n-2, 因為i == n-1時代表已經到達終點
        for (int i = 0; i < nums.length - 1; ++i) {
            rightMost = Math.max(rightMost, i + nums[i]);
            if (i == jumpEnd) {
                ++res;
                jumpEnd = rightMost;
            }
        }
        return res;
    }
}