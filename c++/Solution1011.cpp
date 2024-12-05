//Binary Search O(log(sum - max) * n) O(1), sum=weights總和, max=weights中最大值, n=weights.size()
class Solution1011 {
public:
    int shipWithinDays(vector<int>& weights, int days) {
        int l = 0, r = 0; // 解法跟Solution410一樣
        for (int weight : weights) {
            l = max(l, weight);
            r += weight;
        }

        while (l < r) {
            int mid = l + (r - l) / 2;
            int d = calDay(weights, mid);
            if (d > days) { // 花太多天, 需要增加承載量
                l = mid + 1;
            } else { // 花太少天, 可以減少承載量
                r = mid;
            }
        }
        return l;
    }

    int calDay(vector<int>& weights, int capacity) {
        int sum = 0, day = 1;
        for (int weight : weights) {
            if (sum + weight > capacity) {
                ++day;
                sum = weight;
            } else {
                sum += weight;
            }
        }
        return day;
    }
};