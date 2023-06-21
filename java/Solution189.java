package leetCode.java;

//O(n) O(1)
class Solution189 {
    public void rotate(int[] nums, int k) {
        if(k % nums.length == 0) return;//無變化

        int start = 0, cur = nums[0], index = 0;
        for(int i=0; i<nums.length; i++){
            index = (index + k) % nums.length;
            int temp = nums[index];
            nums[index] = cur;
            cur = temp;

            if(index == start){//回到原點時
                index = ++start;
                cur = nums[index];
            }
        }
    }
}