package leetCode.java;

//Moore Voting Algorithm O(n) O(1)
class Solution169 {
    public int majorityElement(int[] nums) {
        int res = -1;
        int cnt = 0;
        for(int num : nums){
            if(cnt == 0){
                res = num;
                cnt++;
            }else{
                res = res == num ? cnt++ : cnt--;
            }
        }
        return res;
    }
}