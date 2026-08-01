package leetCode.java;

//LR Pointer O(n) O(1)
class Solution11 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            int area = (r - l) * Math.min(height[l], height[r]);
            res = Math.max(res, area);
            if (height[l] < height[r]) { // 水的容量由最小的高度決定, 所以優先去掉小的那一端
                ++l;
            } else {
                --r;
            }
        }
        return res;
    }
}