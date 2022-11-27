//LR Pointer O(n) O(1)
class Solution11 {
public:
    int maxArea(vector<int>& height) {
        int l = 0, r = height.size() - 1, res = 0;
        while(l < r){
            res = max(res, min(height[l], height[r]) * (r - l));
            if(height[l] < height[r]){
                ++l;
            }else{
                --r;
            }
        }
        return res;
    }
};