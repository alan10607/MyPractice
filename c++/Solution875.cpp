//Binary Search O(nlogm) O(1), n = piles.size(), m = max(piles)
class Solution875 {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        int l = 1, r = 0; // 最小值設為1, 至少要吃完
        for (int pile : piles) {
            r = max(r, pile); // 最大值為每次都全吃完
        }

        int res = r;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int hour = calHour(mid, piles);
            if (hour > h) { // 吃太慢, 增加吃的量
                l = mid + 1;
            } else { // hour <= h, 吃太快, 減少吃的量
                r = mid; // 求左界, hour==h時r=mid
            }
        }
        return l; //or return r, l==r
    }

    int calHour(int eat, vector<int>& piles) {
        int hour = 0;
        for (int pile : piles) {
            hour += pile / eat;
            if (pile % eat > 0) {
                ++hour;
            }
        }
        return hour;
    }
};
/*
piles = [3,6,7,11], h = 8

            =res
            v
eat...  3   4   5   6   ... 11
hour    10  8   8   6

題目要回答最少可以吃多少, 是尋找左界

*/