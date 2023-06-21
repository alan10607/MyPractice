package leetCode.java;

//Binary Search O(nlogm) O(1), n = piles.length, m = max(piles)
class Solution875 {
    public int minEatingSpeed(int[] piles, int h) {
        //piles.length <= h, 每小時最多吃max(piles)個香蕉
        int max = 0;
        for(int pile : piles)
            max = Math.max(max, pile);

        int res = max;
        int l = 1;
        int r = max;
        while(l <= r){
            int mid = (l + r) / 2;
            long hour = calcHour(mid, piles);//O(n)
            if(hour <= h){
                r = mid - 1;//吃太快, 要減慢
                res = Math.min(res, mid);//return the minimum k such that she can eat all
            }else{
                l = mid + 1;//吃太慢, 要加快
            }
        }
        return res;
    }

    public long calcHour(int eat, int[] piles){
        long hour = 0;//long否則測試會超過
        for(int pile : piles)
            hour += (pile / eat) + (pile % eat > 0 ? 1 : 0);//無條件進位

        return hour;
    }
}