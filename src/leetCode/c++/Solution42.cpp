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
height  0   1   0   2   1   0   1   3   2   1   2   1
L -> R  0   1   1   2   2   2   2   3   3   3   3   3
R -> L  3   3   3   3   3   3   3   3   2   2   2   1

max     0   1   1   2   2   2   2   3   2   2   2   1
max-hei 0   0   1   0   1   2   1   0   0   1   0   0

res=6

可以透過左右指標一次遍歷完成上述迴圈
*/