//LR Pointer O(n) O(1)
class Solution42 {
public:
    int trap(vector<int>& height) {
        int l = 0, r = height.size() - 1, lMax = 0, rMax = 0, res = 0;
        while(l < r){
            if(height[l] < height[r]){//小的先開始做, 可以確保為min(lMax, rMax)
                lMax = max(lMax, height[l]);
                res += lMax - height[l];
                ++l;
            }else{
                rMax = max(rMax, height[r]);
                res += rMax - height[r];
                --r;
            }
        }
        return res;
    }
};
/*
每個位置能接的雨水量受到左右兩側最短的柱子決定

water = min(leftMax, rightMax) - height[i]

1. L -> R: 預處理每個位置左側最高柱 leftMax[]
2. R -> L: 預處理每個位置右側最高柱 rightMax[]
3. 對每個位置計算: 
    water = min(leftMax[i], rightMax[i]) - height[i]
4. 透過左右指標同時計算

height  0   1   0   2   1   0   1   3   2   1   2   1
L -> R  0   1   1   2   2   2   2   3   3   3   3   3
R -> L  3   3   3   3   3   3   3   3   2   2   2   1

max     0   1   1   2   2   2   2   3   2   2   2   1
max-hei 0   0   1   0   1   2   1   0   0   1   0   0

res=6

可以透過左右指標一次遍歷完成上述迴圈
*/