package leetCode.java;

//O(n) O(1)
class Solution238 {
    public int[] productExceptSelf(int[] nums) {
        //must run in O(n)
        int[] product = new int[nums.length];

        int temp = 1;
        for(int i=0; i<nums.length; i++){
            product[i] = temp;
            temp *= nums[i];//過了之後才乘
        }

        temp = 1;
        for(int i = nums.length - 1; i>=0; i--){
            product[i] *= temp;
            temp *= nums[i];//過了之後才乘
        }

        return product;
    }
}

/* 從自己的前一個開始乘
nums        =  1  2  3  4
product(->) =  1  1  2  6
product(<-) = 24 12  4  1
product     = 24 12  8  6
*/