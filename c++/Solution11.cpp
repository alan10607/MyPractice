//LR Pointer O(n) O(1)
class Solution11 {
public:
    int maxArea(vector<int>& height) {
        int l = 0, r = height.size() - 1, res = 0;
        while(l < r){
            int area = min(height[l], height[r]) * (r - l);
            res = max(res, area);
            if(height[l] < height[r]){//水的容量由最小的高度決定, 所以優先去掉小的那一端
                ++l;
            }else{
                --r;
            }
        }
        return res;
    }
};