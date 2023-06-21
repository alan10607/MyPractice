package leetCode.java;

//LR Pointer O(n) O(1)
class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        //Input Array Is Sorted, 其實就是3sum的內層算法
        int l = 0;
        int r = numbers.length - 1;
        while(l < r){
            int sum = numbers[l] + numbers[r];
            if(sum < target){
                l++;
            }else if(sum > target){
                r--;
            }else{//sum == 0
                return new int[]{l + 1, r + 1};//題目要求回傳第幾個
            }
        }
        return new int[]{};
    }
}