package leetCode.java;

//LR Pointer O(n) O(1)
class Solution42 {
    public int trap(int[] height) {
        int res = 0;
        int l = 0;
        int r = height.length - 1;
        int lMax = 0;
        int rMax = 0;
        while(l < r){
            //比較小的先走, 可以確保為min(lMax,rMax)-height[i]
            if(height[l] < height[r]){
                lMax = Math.max(lMax, height[l]);
                res += lMax - height[l];
                l++;
            }else{
                rMax = Math.max(rMax, height[r]);
                res += rMax - height[r];
                r--;
            }
        }
        return res;
    }
}
/*
lMax   	=  0 1 1 2 2 2 2 3 3 3 3 3
rMax   	=  3 3 3 3 3 3 3 3 2 2 2 1
height 	= [0,1,0,2,1,0,1,3,2,1,2,1]
min(l,r)   0 1 1 2 2 2 2 3 2 2 2 1
min(l,r)-h 0 0 1 0 1 2 1 0 0 1 0 0

res = min(lMax,rMax)-height = 6
*/