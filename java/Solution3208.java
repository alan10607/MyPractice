package leetCode.java;

//Slide Window O(n) O(1), n = colors.length
class Solution3208 {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int res = 0, n = colors.length;
        int alternating = 1;
        for (int i = 1; i < n + k - 1; ++i) {
            if (colors[i % n] != colors[(i - 1) % n]) {
                ++alternating;
            } else {
                alternating = 1;
            }

            if (alternating >= k) {
                ++res;
            }
        }
        return res;
    }
}