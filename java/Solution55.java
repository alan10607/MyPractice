package leetCode.java;

//Greedy O(n) O(1)
class Solution55 {
    public boolean canJump(int[] nums) {
        int rightMost = 0;
        for(int i=0; i<nums.length; i++){
            if(i > rightMost) break;//代表這個位子無法到達

            rightMost = Math.max(rightMost, i + nums[i]);
            if(rightMost >= nums.length - 1)
                return true;
        }
        return false;
    }
}