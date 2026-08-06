package leetCode.java;

//Binary Search O(nlogm) O(1), n = piles.length, m = max(piles)
class Solution875 {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 1; // 最小值設為1, 至少要吃完
        for (int pile : piles) {
            r = Math.max(r, pile); // 最大值為每次都全吃完
        }

        // 要找第一個可以吃完的速度->lower bound
        while (l < r) {
            int mid = l + (r - l) / 2;
            int hour = calcHour(piles, mid);
            // 本題因為hour與速度(mid)是反比關係, 判斷式剛好是版模的相反
            if (hour <= h) { // 花費時間太少, 嘗試增加時間, 減少吃的量
                r = mid;
            } else { // 花費時間太多, 減少時間, 增加吃的量
                l = mid + 1;
            }
        }

        return l;
    }

    private int calcHour(int[] piles, int speed) {
        int hour = 0;
        for (int pile : piles) {
            hour += (pile / speed) + (pile % speed == 0 ? 0 : 1);
        }
        return hour;
    }
}
/*
1 2 3 4 5 6 7 8 9 10 11
N N N Y Y Y Y Y Y Y  Y
          ↑
      最小可行速度

所以:
1. mid 可行 (hour <= h)
- 答案可能在 mid 左邊, 保留 mid
- r = mid

2. mid 不可行 (hour > h)
- 速度太慢, 必須提高速度
- l = mid + 1

*/